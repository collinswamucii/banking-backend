package com.example.banking.controller;

import com.example.banking.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticateAndGenerateToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Getter
    public static class LoginResponse {
        private final String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}