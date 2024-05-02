package com.example.coursel_be.service;

import com.example.coursel_be.request.user.LoginRequest;
import com.example.coursel_be.request.user.UserRequest;
import com.example.coursel_be.request.user.UserUpdateRequest;
import com.example.coursel_be.response.JwtResponse;
import com.example.coursel_be.response.user.UserResponse;

import java.util.List;

public interface UserService {
    boolean saveUser(UserRequest userRequest);

    JwtResponse login(LoginRequest loginRequest);

    String addCourseToUser(Long userId, Long courseId);

    List<UserResponse> getAllUsers();

    String updateUser(UserUpdateRequest userUpdateRequest);


}
