package com.example.coursel_be.service;

import com.example.coursel_be.request.user.LoginRequest;
import com.example.coursel_be.request.user.UserRequest;
import com.example.coursel_be.response.JwtResponse;

public interface UserService {
    boolean saveUser(UserRequest userRequest);

    JwtResponse login(LoginRequest loginRequest);

    String addCourseToUser(Long userId, Long courseId);

    String updateUser(UserRequest userRequest);


}
