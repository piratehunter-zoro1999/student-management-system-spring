package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.*;
import com.example.studentmanagementsystem.exception.InvalidCredentialsException;
import com.example.studentmanagementsystem.exception.UserNotFoundException;
import com.example.studentmanagementsystem.exception.UsernameAlreadyExistsException;
import com.example.studentmanagementsystem.model.Role;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.repository.UserRepository;
import com.example.studentmanagementsystem.security.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.jwtUtil=jwtUtil;
        this.passwordEncoder=passwordEncoder;
    }

    public LoginResponse authenticate(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername());

        if(user == null){
            throw new InvalidCredentialsException("Invalid credentials");
        }

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(),user.getRole());

        LoginResponse response = new LoginResponse();
        response.setToken(token);

        return response;
    }

    public RegisterResponse register(RegisterRequest request){

        User existingUser = userRepository.findByUsername(request.getUsername());

        if(existingUser != null){
            throw new UsernameAlreadyExistsException("username already exist!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // default
        user.setRole(Role.STUDENT);

        userRepository.save(user);

        RegisterResponse response = new RegisterResponse();

        response.setUsername(user.getUsername());
        response.setRole(user.getRole());

        return response;


    }
    public CurrentUserResponse getCurrentUser(){
        Authentication authentication=
                SecurityContextHolder
                .getContext().getAuthentication();

        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UserNotFoundException("user not found!");
        }

        CurrentUserResponse response = new CurrentUserResponse();

        response.setUsername(user.getUsername());
        response.setRole(user.getRole());

        return response;
    }
}
