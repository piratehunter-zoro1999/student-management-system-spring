package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Map<String,Object>> addStudent(@Valid @RequestBody StudentDTO dto){

        //DTO -> Entity conversion
        Student s = new Student();
        s.setName(dto.getName());
        s.setRollNo(dto.getRollNo());
        s.setProgram(dto.getProgram());

        // Call service
        Student saved= service.addStudent(s);
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("status","success");
        response.put("massage","student added successfully!");
        response.put("data",saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllStudents(){
        List<Student> students=service.getAllStudent();
        Map<String,Object> response = new LinkedHashMap<>();

            response.put("status","success");
            if(students.isEmpty()) {
                response.put("massage","No students found!");
            }else {
                response.put("massage", "students fetched successfully");
            }
            response.put("data",students);
            return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getById(@PathVariable Long id){
        Student s = service.getStudentById(id);
        Map<String, Object> response = new LinkedHashMap<>();
        if(s!=null) {
            response.put("status", "success");
            response.put("massage", "student found");
            response.put("data",s);
            return ResponseEntity.ok(response);
        }else{
            response.put("status", "fail");
            response.put("massage", "student not found!");
            response.put("data",null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id){
        boolean delete=service.deleteStudent(id);
        Map<String,Object> response = new LinkedHashMap<>();

        if(delete){
           response.put("status","success");
           response.put("massage","student deleted successfully");
           response.put("data",null);
           return ResponseEntity.ok(response);
        }else{
            response.put("status","fail");
            response.put("massage","student not found!");
            response.put("data",null);
            return ResponseEntity.status(404).body(response);
        }

    }
}
