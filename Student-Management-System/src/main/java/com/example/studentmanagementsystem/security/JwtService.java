package com.example.studentmanagementsystem.security;

import com.example.studentmanagementsystem.exception.JwtAuthenticationException;
import org.springframework.stereotype.Service;



@Service
public class JwtService {

    private final JwtUtil jwtutil = new JwtUtil();

    public void validateToken(String authHeader){

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
             throw new JwtAuthenticationException(" Missing or Invalid Authorization Header");
        }
        String token = authHeader.substring(7);
        String username = jwtutil.extractUsername(token);

        if(!jwtutil.validateToken(token,username)){
             throw new JwtAuthenticationException("Invalid or token Expired");
        }

    }
}
