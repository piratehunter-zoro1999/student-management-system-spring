package com.example.studentmanagementsystem.security;

import com.example.studentmanagementsystem.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key SECRET_KEY = Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey".getBytes());

    // Generate token
    public String generateToken(String username , Role role){
        return Jwts.builder()
                .setSubject(username)
                .claim("role",role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SECRET_KEY)
                .compact();
    }

    // Extract username
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    // Extract role
    public String extractRole(String token){
        return extractClaims(token).get("role",String.class);
    }

    //Extract all claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate token
    public boolean validateToken(String token,String username){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // check expiration
    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before( new Date());
    }
}
