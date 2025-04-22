package com.example.todo.services.auth;


import com.example.todo.dto.auth.AuthRequest;
import com.example.todo.dto.AuthResponse;
import com.example.todo.dto.auth.refreshToken.RefreshTokenRequest;
import com.example.todo.entities.resonse.ApiResponse;
import com.example.todo.entities.roles.Role;
import com.example.todo.entities.user.User;
import com.example.todo.repositories.roleRepositor.RoleRepository;
import com.example.todo.repositories.user.UserRepository;
import com.example.todo.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    public ResponseEntity<ApiResponse<?>> register(AuthRequest request) {

        ApiResponse<?> validationError = validateRequest(request);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        // Check if email or username already exists
        if (userRepository.existsByEmail(request.getUserEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "fail",
                    400,
                    "This email is already in use",
                    Collections.emptyMap()
            ));
        }
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        var user = User.builder()
                .email(request.getUserEmail())
                .username(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(userRole))
                .build();
        System.out.println("Saving user: " + user);

        userRepository.save(user);

        String  accessToken = jwtService.generateToken(user.getEmail());
        String  refreshToken = jwtService.generateRefreshToken(user.getEmail());
        AuthResponse authResponse = new AuthResponse(accessToken,refreshToken,user.getUsername(),user.getEmail());
        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "User registered successfully!",
                authResponse
        ));
    }
    public ApiResponse<AuthResponse> login(AuthRequest request) {
        var user = userRepository.findByEmail(request.getUserEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid credentials");
        }
        String  accessToken = jwtService.generateToken(user.getEmail());
        String  refreshToken = jwtService.generateRefreshToken(user.getEmail());
        AuthResponse authResponse = new AuthResponse(accessToken,refreshToken,user.getUsername(),user.getEmail());
        return new ApiResponse<>("success",HttpStatus.OK.value(),"User logged in successfully" ,authResponse);
    }



    private ApiResponse<?> validateRequest(AuthRequest request) {
        if (request.getUserEmail() == null || request.getUserEmail().isBlank()) {
            return new ApiResponse<>("fail", 400, "Email is required", Collections.emptyMap());
        }
        if (request.getUserName() == null || request.getUserName().isBlank()) {
            return new ApiResponse<>("fail", 400, "Username is required", Collections.emptyMap());
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            return new ApiResponse<>("fail", 400, "Password is required", Collections.emptyMap());
        }
        return null;
    }

    public ResponseEntity<ApiResponse<?>> refreshToken(RefreshTokenRequest request) {

        String userMail = jwtService.extractUsername(request.getRefreshToken());

        if (!jwtService.isTokenValid(request.getRefreshToken(),userMail)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("fail", 401, "Invalid or expired refresh token", Collections.emptyMap()));
        }
        var user = userRepository.findByEmail(userMail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Generate new tokens
        String newAccessToken = jwtService.generateToken(userMail);
        String newRefreshToken = jwtService.generateRefreshToken(userMail);

        AuthResponse authResponse = new AuthResponse(
                newAccessToken,
                newRefreshToken,
                user.getUsername(),
                user.getEmail()
        );

        return ResponseEntity.ok(new ApiResponse<>(
                "success",
                200,
                "Token refreshed successfully",
                authResponse
        ));

    }
}
