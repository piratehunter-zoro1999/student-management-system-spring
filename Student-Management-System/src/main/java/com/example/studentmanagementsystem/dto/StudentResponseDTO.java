package com.example.studentmanagementsystem.dto;

public class StudentResponseDTO {

    private long id;
    private String name;
    private String rollNo;
    private String program;

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
}
