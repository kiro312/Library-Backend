package com.library.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.library.models.ApiResponseModel;
import com.library.library.repositories.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository author_repository;

    @Autowired
    private ApiResponseModel api_response_model;

    public ResponseEntity<ApiResponseModel> getAllAuthors() {
        try {
            api_response_model.setMessage("Success");
            api_response_model.setStatus(true);
            api_response_model.setData(author_repository.findAll());
            return ResponseEntity.ok().body(api_response_model);
        } catch (Exception e) {
            api_response_model.setMessage("Error " + e.getMessage());
            api_response_model.setStatus(false);
            return ResponseEntity.badRequest().body(api_response_model);
        }
    }
}
