package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.Role;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.service.JwtService;
import com.example.coursel_be.service.UserSecurityService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtServiceImpl implements JwtService {
    private static final String KEY_SECRET = "MTIzNDU2NDU5OThEMzIxM0F6eGMzNTE2NTQzMjEzMjE2NTQ5OHEzMTNhMnMxZDMyMnp4M2MyMQ==";

    @Autowired
    private UserSecurityService userSecurityService;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        User user = userSecurityService.findByUserName(username);
        claims.put("id", user.getId());
        claims.put("avatar", user.getAvatar());
        claims.put("fullName", user.getFullName());
        claims.put("enabled", user.getIsDeleted());
        List<Role> roles = user.getListRoles();
        if (!roles.isEmpty()) {
            for (Role role : roles) {
                if (role.getRoleName().equals("ADMIN")) {
                    claims.put("role", "ADMIN");
                    break;
                }
                if (role.getRoleName().equals("CUSTOMER")) {
                    claims.put("role", "CUSTOMER");
                    break;
                }
            }
        }


        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000) )
                .setExpiration(new Date(System.currentTimeMillis() + 100000L * 60 * 60 * 1000) )
                .signWith(SignatureAlgorithm.HS256, getSigneKey())
                .compact();
    }

    private Key getSigneKey() {
        byte[] keyByte = Decoders.BASE64.decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigneKey()).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }


    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }


    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}