package com.example.studentmanagementsystem.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping("/test")
    public String teacherTest(){
        return "Teacher Access Granted";
    }
}
