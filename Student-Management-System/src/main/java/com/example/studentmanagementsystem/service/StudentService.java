package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public Student addStudent(Student s){
        return repo.save(s);
    }
    public List<Student> getAllStudent(){
        return repo.findAll();
    }
    public Student getStudentById(Long id){
        return repo.findById(id).orElse(null);
    }
    public boolean deleteStudent(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

}
