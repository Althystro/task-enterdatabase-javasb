package com.example.database.service;

import com.example.database.bo.CreateUserRequest;
import com.example.database.bo.UserResponse;
import com.example.database.entity.Status;
import com.example.database.entity.UserEntity;
import com.example.database.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setStatus(Status.valueOf(request.getStatus()));
        userEntity = userRepository.save(userEntity);
        UserResponse response = new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus().toString());
        return response;
    }

    @Override
    public UserResponse updateStatus(Long id, String status) {
        if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Inactive")) {
            throw new IllegalArgumentException("Invalid status. Status must be either 'Active' or 'Inactive'.");
        }
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        userEntity.setStatus(Status.valueOf(status));
        userEntity = userRepository.save(userEntity);
        UserResponse response = new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus().toString());
        return response;
    }

    @Override
    public List<UserResponse> searchUsersByStatus(String status) {
        List<UserResponse> userEntities = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            if (userEntity.getStatus() == Status.valueOf(status.toUpperCase())) {
                userEntities.add(new UserResponse(userEntity));
            }
        }
        return userEntities;
    }
}