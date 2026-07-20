package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.*;
import com.example.studentmanagementsystem.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Tag(
        name = "Authentication",
        description = "APIs for user registration, login and authenticated user information."
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService){
        this.authService=authService;
    }



    @PostMapping("/login")
    @Operation(
            summary = "Authenticate user",
            description = "Authenticates a user using username and password and returns a JWT access token upon successful authentication."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User Authenticated successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid username or password."
            )
    }
    )
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequest request){


        LoginResponse loginResponse = authService.authenticate(request);

        Map<String,Object> response = new LinkedHashMap<>();

        response.put("status","success");
        response.put("message","Login successful");
        response.put("data",loginResponse);

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/register")
    @Operation(
            summary ="Register a new user",
            description = "Registers a new user using username and password.Return registered user's information,including username and role on successful registration "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists."
            )
    }
    )
    public ResponseEntity<Map<String,Object>> register(@RequestBody RegisterRequest request){
        Map<String,Object> response = new LinkedHashMap<>();

        RegisterResponse user = authService.register(request);
        response.put("status","success");
        response.put("message","user registered successfully!");
        response.put("data",user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Get current user",
            description = "Return the authenticated user information,including username and role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Current user information retrieved successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            )
    })
    public ResponseEntity<Map<String,Object>> getCurrentUser(){
        Map<String,Object> response = new LinkedHashMap<>();

        CurrentUserResponse user = authService.getCurrentUser();

        response.put("status","success");
        response.put("message","current user fetched successfully");
        response.put("data",user);

        return ResponseEntity.ok(response);
    }

}
