package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.LoginRequest;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.repository.UserRepository;
import com.example.studentmanagementsystem.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    AuthService(UserRepository userRepository,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.jwtUtil=jwtUtil;
    }

    public String authenticate(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername());

        if(user == null){
            throw new RuntimeException(
                    "invalid credentials"
            );
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return token;
    }
}
