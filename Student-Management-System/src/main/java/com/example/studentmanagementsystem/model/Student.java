package com.example.studentmanagementsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message="name cannot be empty!")
    private String name;

    @NotBlank(message="roll number cannot be empty!")
    private String rollNo;

    @NotBlank(message="program cannot be empty!")
    private String program;

    // getter
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getProgram() {
        return program;
    }

    //setter
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
