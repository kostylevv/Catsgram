package com.vkostylev.catsgram.dto;

import lombok.Data;

@Data
public class NewUserRequest {
    private String username;
    private String email;
    private String password;
}

