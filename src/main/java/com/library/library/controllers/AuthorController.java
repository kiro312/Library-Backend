package com.library.library.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.library.models.ApiResponseModel;
import com.library.library.services.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    
    @Autowired
    private AuthorService author_service;

    @GetMapping("")
    public ResponseEntity<ApiResponseModel> getAllAuthors() {
        return author_service.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseModel> getAuthorById(@PathVariable Integer id) {
        return author_service.getAuthorById(id);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponseModel> createAuthor(@RequestBody Map<String, Object> request_body) {
        return author_service.createAuthor(request_body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseModel> updateAuthor(@PathVariable Integer id, @RequestBody Map<String, Object> request_body) {
        return author_service.updateAuthor(id, request_body);
    }
    
}
