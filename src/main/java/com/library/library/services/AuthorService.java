package com.library.library.services;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    private String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public ResponseEntity<ApiResponseModel> getAllAuthors() {
        try {
            List<Author> authors = author_repository.findAll();
            return ResponseEntity.ok().body(new ApiResponseModel("getting all authors successfully", true, authors));
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
            return ResponseEntity.ok().body(new ApiResponseModel("getting author successfully", true, author));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseModel("Error " + e.getMessage(), false, null));
        }
    }

    public ResponseEntity<ApiResponseModel> createAuthor(Map<String, Object> request_body) {
        try {
            // 1 - validate request body
            if (!validateRequestBody(request_body)) {
                return ResponseEntity.badRequest().body(new ApiResponseModel("Validation Error", false, null));
            }
            // 2 - create author
            Author author = new Author();
            author.setAuthorName(request_body.get("author_name").toString());
            author.setAuthorEmail(request_body.get("author_email").toString());
            author.setAuthorBio(request_body.get("author_bio").toString());
            author_repository.save(author);
            // 3 - return response
            return new ResponseEntity<>(
                    new ApiResponseModel("Author Created Successfully", true, author),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseModel("Error " + e.getMessage(), false, null));
        }
    }

    public ResponseEntity<ApiResponseModel> updateAuthor(Integer id, Map<String, Object> request_body) {
        try {
            // 0 - check if author exists
            if (!author_repository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // 1 - validate request body
            if (!validateRequestBody(request_body)) {
                return ResponseEntity.badRequest().body(new ApiResponseModel("Validation Error", false, null));
            }
            // 2 - update author
            Author author = author_repository.findById(id).orElse(null);
            author.setAuthorName(request_body.get("author_name").toString());
            author.setAuthorEmail(request_body.get("author_email").toString());
            author.setAuthorBio(request_body.get("author_bio").toString());
            author_repository.save(author);
            // 3 - return response
            return ResponseEntity.ok().body(new ApiResponseModel("Author Updated Successfully", true, author));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseModel("Error " + e.getMessage(), false, null));
        }
    }

    public boolean validateRequestBody(Map<String, Object> request_body) {
        if (!request_body.containsKey("author_name") || !request_body.containsKey("author_email")
                || !request_body.containsKey("author_bio")) {
            return false;
        }
        // check not null
        if (request_body.get("author_name") == null || request_body.get("author_email") == null
                || request_body.get("author_bio") == null) {
            return false;
        }
        // check empty
        if (request_body.get("author_name").toString().isEmpty()
                || request_body.get("author_email").toString().isEmpty()
                || request_body.get("author_bio").toString().isEmpty()) {
            return false;
        }
        // validate email
        if (!pattern.matcher(request_body.get("author_email").toString()).matches()) {
            return false;
        }
        return true;
    }
}
