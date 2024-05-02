package com.example.coursel_be.controller;

import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.request.user.LoginRequest;
import com.example.coursel_be.request.user.UserCourseRequest;
import com.example.coursel_be.request.user.UserRequest;
import com.example.coursel_be.request.user.UserUpdateRequest;
import com.example.coursel_be.response.error.ApiResponse;
import com.example.coursel_be.response.JwtResponse;
import com.example.coursel_be.response.user.UserResponse;
import com.example.coursel_be.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                if (isSaved) {
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
            if (loginRequest.getUserName() != null && loginRequest.getPassword() != null) {
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

    @PostMapping("/addUserCourse")
    public ResponseEntity<?> addUserCourse(@RequestBody UserCourseRequest userCourseRequest){
        try {
            if (userCourseRequest != null) {
                String message = userService.addCourseToUser(userCourseRequest.getUserId(), userCourseRequest.getCourseId());
                ApiResponse<String> apiResponse = new ApiResponse<>();
                apiResponse.setCode(200);
                apiResponse.setMessage(message);
                return ResponseEntity.ok(apiResponse);
            }
            return ResponseEntity.badRequest().body("Add course to user failed");
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(errorResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/getUserInfo/{userId}")
//    public ResponseEntity<?> getUserInfo(@PathVariable Long userId){
//        try {
//            if (userId != null) {
//                return ResponseEntity.ok(userService.getUserInfoById(userId));
//            }
//            return ResponseEntity.badRequest().body("User not found");
//        } catch (AppException e) {
//            ApiResponse<String> errorResponse = new ApiResponse<>();
//            errorResponse.setCode(e.getErrorCode().getCode());
//            errorResponse.setMessage(e.getErrorCode().getMessage());
//            return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(errorResponse);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers(){
        try {
            ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
            apiResponse.setCode(200);
            apiResponse.setResult(userService.getAllUsers());
            return ResponseEntity.ok(apiResponse);
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(errorResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        try {
            if (userUpdateRequest != null) {
                String message = userService.updateUser(userUpdateRequest);
                ApiResponse<String> apiResponse = new ApiResponse<>();
                apiResponse.setCode(200);
                apiResponse.setMessage(message);
                return ResponseEntity.ok(apiResponse);
            }
            return ResponseEntity.badRequest().body("Update user failed");
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(errorResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello");
    }
}
