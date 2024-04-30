package com.example.coursel_be.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {
    public String generateToken(String userName);

    public Date extractExpiration(String token);

    public String extractUsername(String token);

    public Boolean validateToken(String token, UserDetails userDetails);

}
