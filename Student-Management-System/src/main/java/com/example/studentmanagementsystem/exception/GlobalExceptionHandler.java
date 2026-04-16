package com.example.studentmanagementsystem.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class )
    public Map<String,Object> handleValidation(MethodArgumentNotValidException ex){

        Map<String,Object> response= new LinkedHashMap<>();
        response.put("status","fail");
        response.put("message","validation failed");

        Map<String,String> errors= new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(), error.getDefaultMessage());
        });

        response.put("error",errors);

        return response;
    }

}
