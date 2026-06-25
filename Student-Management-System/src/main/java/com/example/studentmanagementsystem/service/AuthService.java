package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.CurrentUserResponse;
import com.example.studentmanagementsystem.dto.LoginRequest;
import com.example.studentmanagementsystem.dto.RegisterRequest;
import com.example.studentmanagementsystem.dto.RegisterResponse;
import com.example.studentmanagementsystem.model.Role;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.repository.UserRepository;
import com.example.studentmanagementsystem.security.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String authenticate(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername());

        if(user == null){
            throw new RuntimeException("invalid credentials");
        }

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("invalid password!");
        }
        String token = jwtUtil.generateToken(user.getUsername(),user.getRole());

        return token;
    }

    public RegisterResponse register(RegisterRequest request){

        User existingUser = userRepository.findByUsername(request.getUsername());

        if(existingUser != null){
            throw new RuntimeException("username already exist!");
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
            throw new RuntimeException("user not found!");
        }

        CurrentUserResponse response = new CurrentUserResponse();

        response.setUsername(user.getUsername());
        response.setRole(user.getRole());

        return response;
    }
}
