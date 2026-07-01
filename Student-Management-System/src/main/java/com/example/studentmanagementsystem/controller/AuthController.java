package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.*;
import com.example.studentmanagementsystem.service.AuthService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequest request){


        LoginResponse loginResponse = authService.authenticate(request);

        Map<String,Object> response = new LinkedHashMap<>();

        response.put("status","success");
        response.put("message","Login successful");
        response.put("data",loginResponse);

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody RegisterRequest request){
        Map<String,Object> response = new LinkedHashMap<>();

        RegisterResponse user = authService.register(request);
        response.put("status","success");
        response.put("message","user registered successfully!");
        response.put("data",user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String,Object>> getCurrentUser(){
        Map<String,Object> response = new LinkedHashMap<>();

        CurrentUserResponse user = authService.getCurrentUser();

        response.put("status","success");
        response.put("message","current user fetched successfully");
        response.put("data",user);

        return ResponseEntity.ok(response);
    }

}
