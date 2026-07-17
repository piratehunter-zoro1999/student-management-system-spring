package com.example.studentmanagementsystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
       return new OpenAPI()
               .info(
                       new Info()
                               .title("Student Management System API")
                               .version("1.0")
                               .description("Secure REST API built using Spring Boot, Spring Security, JWT Authentication and MySQL.")
                               .contact(
                                       new Contact()
                                               .name("Kushpinder Singh")
                                               .email("temp@123.com")
                                               .url("https://github.com/piratehunter-zoro1999")
                               )
               )
               .components(
                       new Components()
                               .addSecuritySchemes(
                                       "bearerAuth",
                                       new SecurityScheme()
                                               .type(SecurityScheme.Type.HTTP)
                                               .scheme("bearer")
                                               .bearerFormat("jwt")
                               )

               )
               .addSecurityItem(
                       new SecurityRequirement()
                               .addList("bearerAuth")
               );

    }
}
