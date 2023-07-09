package com.library.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
