package com.example.studentmanagementsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private final String SECRET_KEY = "mysecretkey";

    // Generate token
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    // Extract username
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    //Extract all claims
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
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
