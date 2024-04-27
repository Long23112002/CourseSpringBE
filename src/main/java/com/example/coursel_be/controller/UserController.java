package com.example.coursel_be.controller;

import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.request.LoginRequest;
import com.example.coursel_be.request.UserRequest;
import com.example.coursel_be.response.ApiResponse;
import com.example.coursel_be.response.JwtResponse;
import com.example.coursel_be.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        try {
            if (userRequest != null) {
                boolean isSaved = userService.saveUser(userRequest);
                if(isSaved){
                    ApiResponse<User> apiResponse = new ApiResponse<>();
                    apiResponse.setCode(201);
                    apiResponse.setMessage("User registered successfully");
                    return ResponseEntity.ok(apiResponse);
                }
            }
            return ResponseEntity.badRequest().body("User registration failed");
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(errorResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            if (loginRequest.getUserName() != null && loginRequest.getPassword() != null){
                JwtResponse jwtResponse = userService.login(loginRequest);
                ApiResponse<JwtResponse> apiResponse = new ApiResponse<>();
                apiResponse.setCode(200);
                apiResponse.setMessage("Login successful");
                apiResponse.setResult(jwtResponse);
                return ResponseEntity.ok(apiResponse);
            }
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(errorResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Login failed", null));
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello");
    }
}
