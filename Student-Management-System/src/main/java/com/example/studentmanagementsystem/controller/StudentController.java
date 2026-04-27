package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.dto.StudentResponseDTO;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // dto mapping method
    private StudentResponseDTO mapToDTO(Student s){
        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setProgram(s.getProgram());
        dto.setRollNo(s.getRollNo());

        return dto;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> addStudent(@Valid @RequestBody StudentDTO dto){

        //DTO -> Entity conversion
        Student s = new Student();
        s.setName(dto.getName());
        s.setRollNo(dto.getRollNo());
        s.setProgram(dto.getProgram());

        // Call service
        Student saved= service.addStudent(s);

        // Entity -> responseDTO
        StudentResponseDTO responseDTO = mapToDTO(saved);


        Map<String,Object> response = new LinkedHashMap<>();
        response.put("status","success");
        response.put("message","student added successfully!");
        response.put("data",responseDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllStudents(){

        // Call service
        List<Student> students=service.getAllStudents();

        // Entity -> responseDTO
        List<StudentResponseDTO> dtoList = new ArrayList<>();
        for(Student s : students){
            StudentResponseDTO dto = mapToDTO(s);


            dtoList.add(dto);
        }

        Map<String,Object> response = new LinkedHashMap<>();

            response.put("status","success");
            if(students.isEmpty()) {
                response.put("message","No students found!");
            }else {
                response.put("message", "students fetched successfully");
            }
            response.put("data",dtoList); // dtoList loaded not Entity
            return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getById(@PathVariable Long id){
        Student s = service.getStudentById(id);



        Map<String, Object> response = new LinkedHashMap<>();
        if(s!=null) {
            // Entity-> ResponseDTO
            StudentResponseDTO responseDTO = mapToDTO(s);


            response.put("status", "success");
            response.put("message", "student found");
            response.put("data",responseDTO); // dto response instead of Entity
            return ResponseEntity.ok(response);
        }else{
            response.put("status", "fail");
            response.put("message", "student not found!");
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
           response.put("message","student deleted successfully");
           response.put("data",null);
           return ResponseEntity.ok(response);
        }else{
            response.put("status","fail");
            response.put("message","student not found!");
            response.put("data",null);
            return ResponseEntity.status(404).body(response);
        }

    }
}
