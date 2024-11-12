package com.project.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dtos.LoginDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String secretKey;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
       
        if ("admin".equals(dto.getUserid()) && "admin".equals(dto.getPwd())) {
            String token = Jwts.builder()
                    .setSubject(dto.getUserid())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}