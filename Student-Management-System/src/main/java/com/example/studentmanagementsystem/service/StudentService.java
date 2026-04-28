package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public Student addStudent(Student s){
        return repo.save(s);
    }
    public List<Student> getAllStudents(){
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
    public Student updateStudent(Long id , StudentDTO dto){
        Student existing = repo.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

         existing.setName(dto.getName());
         existing.setRollNo(dto.getRollNo());
         existing.setProgram(dto.getProgram());

         return repo.save(existing);
    }

}
