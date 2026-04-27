package com.example.studentmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;

public class StudentDTO {

    @NotBlank(message="name can't be empty")
    private String name;

    @NotBlank(message="roll no can't be empty")
    private String rollNo;

    @NotBlank(message="program can't be empty")
    private String program;

    public void setName(@NotBlank(message = "name can't be empty") String name) {
        this.name = name;
    }

    public void setRollNo(@NotBlank(message = "roll no can't be empty") String rollNo) {
        this.rollNo = rollNo;
    }

    public void setProgram(@NotBlank(message = "program can't be empty") String program) {
        this.program = program;
    }

    public @NotBlank(message = "name can't be empty") String getName() {
        return name;
    }

    public @NotBlank(message = "roll no can't be empty") String getRollNo() {
        return rollNo;
    }

    public @NotBlank(message = "program can't be empty") String getProgram() {
        return program;
    }
}
