package com.example.studentmanagementsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "error");
        response.put("message", "Validation failed");

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        response.put("errors", errors);

        return response;
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity< Map<String,Object>> handleJwtException(
            JwtAuthenticationException ex){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("status","error");
        response.put("message",ex.getMessage());
        response.put("data",null);

        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity< Map<String,Object>> handleInvalidException(
            InvalidCredentialsException ex){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("status","error");
        response.put("message",ex.getMessage());
        response.put("data",null);

        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleUsernameExistException(
            UsernameAlreadyExistsException ex
    ){
        Map<String ,Object> response = new LinkedHashMap<>();
        response.put("status","error");
        response.put("message",ex.getMessage());
        response.put("data",null);

        return ResponseEntity.status(409).body(response);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFoundException(
            UserNotFoundException ex
    ){
        Map<String ,Object> response = new LinkedHashMap<>();
        response.put("status","error");
        response.put("message",ex.getMessage());
        response.put("data",null);

        return ResponseEntity.status(404).body(response);

    }
}