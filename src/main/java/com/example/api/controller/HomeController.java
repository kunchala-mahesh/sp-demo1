package com.example.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    @Hidden
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "Spring Boot Basic APIs");
        response.put("version", "1.0.0");
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("Swagger UI", "/swagger-ui.html");
        endpoints.put("OpenAPI Spec", "/api-docs");
        endpoints.put("Posts API", "/posts");
        endpoints.put("Products API", "/products");
        endpoints.put("Users API", "/users");
        endpoints.put("H2 Database Console", "/h2-console");

        response.put("endpoints", endpoints);
        return ResponseEntity.ok(response);
    }
}
