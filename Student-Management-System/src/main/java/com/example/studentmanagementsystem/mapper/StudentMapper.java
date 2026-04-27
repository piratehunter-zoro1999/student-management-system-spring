package com.example.studentmanagementsystem.mapper;

import com.example.studentmanagementsystem.dto.StudentResponseDTO;
import com.example.studentmanagementsystem.model.Student;

public class StudentMapper {

    // dto to student mapper
    public static StudentResponseDTO toDTO(Student s){
        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setProgram(s.getProgram());
        dto.setRollNo(s.getRollNo());

        return dto;
    }
}
