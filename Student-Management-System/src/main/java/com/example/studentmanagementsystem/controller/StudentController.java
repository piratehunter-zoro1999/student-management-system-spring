package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.dto.StudentResponseDTO;
import com.example.studentmanagementsystem.mapper.StudentMapper;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.security.JwtService;
import com.example.studentmanagementsystem.service.StudentService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Tag(
        name="students",
        description = "APIs for managing student records,including create,retrieve,update,delete,search and pagination"
)
@RestController
@RequestMapping ("/students")
public class StudentController {

    @Hidden
    @GetMapping("/test")
    public String studentTest(){
        return "Student Access Granted";
    }

    @Autowired
    private StudentService service;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    @Operation(
            summary = "Create a new student",
            description = "Creates a new student record and returns the registered student's information upon successful creation."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student created successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid student data."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            )
    })
    public ResponseEntity<Map<String,Object>> addStudent(

            @Valid @RequestBody StudentDTO dto){

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
    @Operation(
            summary ="Retrieves all students",
            description = "Returns a list of all registered students"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Students retrieved successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            )
    })
    public ResponseEntity<Map<String,Object>> getAllStudents(){



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
    @Operation(
            summary = "Retrieve a student by ID",
            description = "Returns details of the student for the specified ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student retrieved successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found."
            )
    })
    public ResponseEntity<Map<String,Object>> getById(
            @Parameter(
                    description = "Unique ID of student.",
                    example = "1"
            )
            @PathVariable Long id){


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
    @Operation(
           summary = "Deletes student by ID",
           description = "Deletes student records for the specified ID "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found."
            )
    })
    public ResponseEntity<Map<String,Object>> delete(
            @Parameter(
                    description = "Unique ID of student.",
                    example = "1"
            )
            @PathVariable Long id){



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
    @Operation(
            summary = "Update student by ID",
            description = "Updates student details for the specified ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student updated successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid student data."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found."
            )
    })
    public ResponseEntity<Map<String,Object>> updateStudent(
            @Parameter(
                    description = "Unique ID of student.",
                    example = "1"
            )
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO dto){


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
    @Operation(
            summary = "Search students by name",
            description = "Returns a list of students whose names match the specified search keyword."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Students retrieved successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            )
    })
    public ResponseEntity<Map<String,Object>> searchStudent(
            @Parameter(
                    description = "Starting name or partial name used for searching.",
                    example = "rahul"
            )
            @RequestParam String name){


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
    @Operation(
            summary = "Retrieve students with pagination",
            description = "Returns a paginated list of registered students based on the requested page number and page size."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student retrieved successfully."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. A valid JWT access token is required."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid page number or page size."
            )

    })
    public ResponseEntity<Map<String,Object>> getStudentsWithPagination(
            @Parameter(
                    description = "Page number (starts from 0).",
                    example = "0"
            )
            @RequestParam int page,
            @Parameter(
                    description = "Number of students to return per page.",
                    example = "10"

            )
            @RequestParam int size){


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
