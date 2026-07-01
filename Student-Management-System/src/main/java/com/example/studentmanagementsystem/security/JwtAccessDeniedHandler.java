package com.example.studentmanagementsystem.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
           response.setStatus(HttpServletResponse.SC_FORBIDDEN);
           response.setContentType("application/json");

        Map<String,Object> body = new LinkedHashMap<>();

        body.put("status","error");
        body.put("message","Access denied");
        body.put("data",null);

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(response.getOutputStream(),body);
    }
}
