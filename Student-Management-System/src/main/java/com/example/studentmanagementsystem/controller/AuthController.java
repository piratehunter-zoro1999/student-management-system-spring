package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.LoginRequest;
import com.example.studentmanagementsystem.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtutil = new JwtUtil();

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginRequest request){

        // Hardcoded check (for now)
        if("admin".equals(request.getUsername()) && "1234".equals(request.getPassword())){

            String token = jwtutil.generateToken(request.getUsername());
            Map<String,Object> response = new HashMap<>();

            response.put("token",token);
            return response;
        }
        else{
            throw new RuntimeException("Invalid credentials");
        }
    }
}
