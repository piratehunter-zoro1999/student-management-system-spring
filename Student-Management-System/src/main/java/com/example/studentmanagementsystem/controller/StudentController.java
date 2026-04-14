package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student addStudent(@RequestBody Student s){
        return service.addStudent(s);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return service.getAllStudent();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id){
        return service.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        if(service.deleteStudent(id)){
            return "student deleted successfully!";
        }
        return "student not found!";
    }
}
