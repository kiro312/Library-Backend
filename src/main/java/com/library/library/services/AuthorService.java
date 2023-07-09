package com.library.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.library.models.ApiResponseModel;
import com.library.library.models.Author;
import com.library.library.repositories.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository author_repository;

    public ResponseEntity<ApiResponseModel> getAllAuthors() {
        try {
            List<Author> authors = author_repository.findAll();
            return ResponseEntity.ok().body(new ApiResponseModel("Success", true, authors));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseModel("Error " + e.getMessage(), false, null));
        }
    }

    public ResponseEntity<ApiResponseModel> getAuthorById(Integer id) {
        try {
            Author author = author_repository.findById(id).orElse(null);
            if (author == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(new ApiResponseModel("Success", true, author));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseModel("Error " + e.getMessage(), false, null));
        }
    }
}
