package com.example.coursel_be.service;

import com.example.coursel_be.entity.User;
import com.example.coursel_be.request.LoginRequest;
import com.example.coursel_be.request.UserRequest;
import com.example.coursel_be.response.JwtResponse;

public interface UserService {
     boolean saveUser(UserRequest userRequest);
     JwtResponse login(LoginRequest loginRequest);
}
