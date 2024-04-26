package com.example.coursel_be.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String generateToken(String userName);
    public Date extractExpiration(String token);
    public String extractUsername(String token);
    public Boolean validateToken(String token, UserDetails userDetails);

}
