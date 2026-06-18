package com.example.studentmanagementsystem.config;

import com.example.studentmanagementsystem.model.Role;
import com.example.studentmanagementsystem.model.User;
import com.example.studentmanagementsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void run(String... args)  {
       User admin = userRepository.findByUsername("admin");

       if(admin == null){
           User user = new User();
           user.setUsername("admin");
           user.setPassword("1234");
           user.setRole(Role.ADMIN);

           userRepository.save(user);
       }
    }
}
