package com.example.database.bo;

import com.example.database.entity.UserEntity;

public class UserResponse {
    private Long id;
    private String name;
    private String status;

    public UserResponse(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public UserResponse(UserEntity userEntity) {
        if (userEntity == null) {
            return;
        }
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.status = String.valueOf(userEntity.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}