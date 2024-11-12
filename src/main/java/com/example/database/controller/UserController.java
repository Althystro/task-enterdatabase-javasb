package com.example.database.controller;

import com.example.database.bo.CreateUserRequest;
import com.example.database.bo.UserResponse;
import com.example.database.entity.UserEntity;
import com.example.database.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET endpoint to retrieve all users
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);

        // Check if the response is not null (indicating a successful creation)
        if (response != null) {
            // Return a 201 Created status code along with the created user data
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            // Handle the case where the creation was not successful (e.g., validation failed)
            // You can return a different status code or error message as needed
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PutMapping("/updateStatus/{userId}/{status}")
    public ResponseEntity<String> updateStatus(@PathVariable Long userId, @PathVariable String status) {
        try {
            UserResponse updateResponse = userService.updateStatus(userId, status);
            if (updateResponse != null) {
                return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @GetMapping("/searchUsers/{status}")
    public ResponseEntity<List<UserResponse>> searchUsers(@PathVariable String status) {
        try {
            List<UserResponse> filteredUsers = userService.searchUsersByStatus(status);

            return ResponseEntity.status(HttpStatus.OK).body(filteredUsers);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
