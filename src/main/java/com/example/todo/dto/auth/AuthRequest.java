package com.example.todo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthRequest {
    private String userEmail;
    private String userName;
    private String password;
}

