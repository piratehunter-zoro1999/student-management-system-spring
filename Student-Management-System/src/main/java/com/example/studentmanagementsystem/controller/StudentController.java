package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.dto.StudentResponseDTO;
import com.example.studentmanagementsystem.mapper.StudentMapper;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.security.JwtUtil;
import com.example.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisAnnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/students")
public class StudentController {
    private void validateJwt(String authHeader){
        JwtUtil jwtutil = new JwtUtil();

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String username = jwtutil.extractUsername(token);

        if(!jwtutil.validateToken(token,username)){
             throw new RuntimeException("Invalid or expired token");
        }
    }
    @Autowired
    private StudentService service;



    @PostMapping
    public ResponseEntity<Map<String,Object>> addStudent(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody StudentDTO dto){

        validateJwt(authHeader); // Auth check

        // Call service
        Student saved= service.addStudent(dto);

        // Entity -> responseDTO
        StudentResponseDTO responseDTO = StudentMapper.toDTO(saved);


        Map<String,Object> response = new LinkedHashMap<>();
        response.put("status","success");
        response.put("message","student added successfully!");
        response.put("data",responseDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllStudents(
            @RequestHeader("Authorization") String authHeader){

        validateJwt(authHeader); // Auth check

        // Call service
        List<Student> students=service.getAllStudents();

        // Entity -> responseDTO
        List<StudentResponseDTO> dtoList = new ArrayList<>();
        for(Student s : students){
            StudentResponseDTO dto = StudentMapper.toDTO(s);


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
    public ResponseEntity<Map<String,Object>> getById(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id){

        validateJwt(authHeader); // Auth check

        Student s = service.getStudentById(id);

        Map<String, Object> response = new LinkedHashMap<>();
        if(s!=null) {
            // Entity-> ResponseDTO
            StudentResponseDTO responseDTO = StudentMapper.toDTO(s);


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
    public ResponseEntity<Map<String,Object>> delete(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id){

        validateJwt(authHeader); // Auth check

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
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateStudent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO dto){

        validateJwt(authHeader); // Auth check

        Student update= service.updateStudent(id,dto);
        Map<String,Object> response = new LinkedHashMap<>();

        if(update != null){
            StudentResponseDTO responseDTO = StudentMapper.toDTO(update);

            response.put("status","success");
            response.put("message","Student updated successfully");
            response.put("data",responseDTO);

            return ResponseEntity.ok(response);
        }else{
            response.put("status","fail");
            response.put("message","student not found!");
            response.put("data",null);

            return ResponseEntity.status(404).body(response);
        }

    }

    @GetMapping("/search")
    public ResponseEntity<Map<String,Object>> searchStudent(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String name){
        // Auth check
        validateJwt(authHeader);

         List<Student> students = service.searchByName(name);

         List<StudentResponseDTO> dtoList = new ArrayList<>();

         for(Student s : students ){
             dtoList.add(StudentMapper.toDTO(s));
         }

         Map<String,Object> response = new LinkedHashMap<>();

         response.put("status","success");

         if(students.isEmpty()){
             response.put("message","students not found!");
         }else{
             response.put("message","students fetched successfully");
         }
         response.put("data",dtoList);

         return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String,Object>> getStudentsWithPagination(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam int page,
            @RequestParam int size){
        // Auth check
        validateJwt(authHeader);

        if(page < 0 || size < 0){
            Map<String,Object> response = new LinkedHashMap<>();
            response.put("status","fail");
            response.put("message","invalid page or size");
            response.put("data",null);

            return ResponseEntity.badRequest().body(response);
        }
        Page<Student> studentPage = service.getStudentsWithPagination(page,size);

        List<StudentResponseDTO> dtoList = new ArrayList<>();

        for(Student s : studentPage.getContent()){
            dtoList.add(StudentMapper.toDTO(s));
        }

        Map<String,Object> response = new LinkedHashMap<>();

        response.put("status","success");

        response.put("message",
                studentPage.getContent().isEmpty()?"students not found!"
                        :"students fetched successfully");
        //data empty or not
        response.put("data",dtoList);

        // pagination info (always present)

        response.put("currentPage",studentPage.getNumber());
        response.put("pageSize",studentPage.getSize());
        response.put("totalPages",studentPage.getTotalPages());
        response.put("totalElement",studentPage.getNumberOfElements());

        return ResponseEntity.ok(response);


    }

}
