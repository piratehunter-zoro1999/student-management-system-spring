package com.example.studentmanagementsystem.security;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtUtil jwtutil = new JwtUtil();

    public void validateToken(String authHeader){

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
             throw new RuntimeException(" Missing or Invalid Authorization Header");
        }
        String token = authHeader.substring(7);
        String username = jwtutil.extractUsername(token);

        if(!jwtutil.validateToken(token,username)){
             throw new RuntimeException("Invalid or token Expired");
        }

    }
}
