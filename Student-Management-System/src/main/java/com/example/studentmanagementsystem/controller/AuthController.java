package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.LoginRequest;
import com.example.studentmanagementsystem.security.JwtUtil;
import com.example.studentmanagementsystem.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService){
        this.authService=authService;
    }




    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginRequest request){


        String token = authService.authenticate(request);

        Map<String,Object> response = new LinkedHashMap<>();

        response.put("token",token);

        return response;
    }
}
