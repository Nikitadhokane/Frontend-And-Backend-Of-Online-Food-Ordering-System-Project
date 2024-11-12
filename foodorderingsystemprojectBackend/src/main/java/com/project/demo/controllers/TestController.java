package com.project.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/exception/{id}")
    public ResponseEntity<String> testException(@PathVariable("id") int id) {
        if (id < 1) {
            throw new ResourceNotFoundException("TestResource", "id", id);
        }
        return ResponseEntity.ok("Resource found");
    }
}