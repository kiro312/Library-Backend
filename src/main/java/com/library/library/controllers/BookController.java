package com.library.library.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.models.ApiResponseModel;
import com.library.library.services.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<ApiResponseModel> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseModel> getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponseModel> createBook(@RequestBody Map<String, Object> request_body) {
        return bookService.createBook(request_body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseModel> updateBook(@PathVariable Integer id, @RequestBody Map<String, Object> request_body) {
        return bookService.updateBook(id, request_body);
    }
}
