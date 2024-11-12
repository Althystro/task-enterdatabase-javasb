package com.example.database.service;

import com.example.database.bo.CreateUserRequest;
import com.example.database.bo.UserResponse;
import com.example.database.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserEntity> getAllUsers();

    UserResponse createUser(CreateUserRequest request);

    UserResponse updateStatus(Long id, String status);

    List<UserResponse> searchUsersByStatus(String status);

}
