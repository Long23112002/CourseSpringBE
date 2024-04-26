package com.example.coursel_be.service;

import com.example.coursel_be.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {
    public User findByUserName(String userName);
}
