package com.library.library.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.library.models.ApiResponseModel;
import com.library.library.models.Author;
import com.library.library.models.Book;
import com.library.library.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository book_repository;

    @Autowired
    private AuthorService author_service;

    public ResponseEntity<ApiResponseModel> getAllBooks() {
        try {
            List<Book> books = book_repository.findAll();
            // check if books is empty
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(new ApiResponseModel("getting all books successfully", true, books));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseModel("Error getting books: " + e.getMessage(), false, null));
        }
    }

    public ResponseEntity<ApiResponseModel> getBookById(Integer id) {
        try {
            Book book = book_repository.findById(id).orElse(null);
            if (book == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(new ApiResponseModel("getting book successfully", true, book));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseModel("Error getting book: " + e.getMessage(), false, null));
        }
    }

    public ResponseEntity<ApiResponseModel> createBook(Map<String, Object> request_body) {
        try {
            // 1 - validate request body
            if (!validateRequestBody(request_body)) {
                return ResponseEntity.badRequest().body(new ApiResponseModel("Validation Error", false, null));
            }
            // 2 - create book
            Book book = new Book();
            book.setBookTitle(request_body.get("book_title").toString());
            book.setBookDescription(request_body.get("book_description").toString());
            book.setBookImage(request_body.get("book_image").toString());
            // 2.1 - get author
            Author author = author_service.geAuthor(Integer.parseInt(request_body.get("author_id").toString()));
            book.setBookAuthor(author);
            // 2.2 - save book
            book_repository.save(book);
            // 3 - return response
            return ResponseEntity.ok().body(new ApiResponseModel("book created successfully", true, book));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseModel("Error creating book: " + e.getMessage(), false, null));
        }
    }

    public ResponseEntity<ApiResponseModel> updateBook(Integer id, Map<String, Object> request_body) {
        try {
            // 0 - check if book exists
            Book book = book_repository.findById(id).orElse(null);
            if (book == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // 1 - validate request body
            if (!validateRequestBody(request_body)) {
                return ResponseEntity.badRequest().body(new ApiResponseModel("Validation Error", false, null));
            }

            // 2 - update book
            book.setBookTitle(request_body.get("book_title").toString());
            book.setBookDescription(request_body.get("book_description").toString());
            book.setBookImage(request_body.get("book_image").toString());
            // 3 - get author
            Author author = author_service.geAuthor(Integer.parseInt(request_body.get("author_id").toString()));
            book.setBookAuthor(author);
            // 3.1 - save book
            book_repository.save(book);
            // 4 - return response
            return ResponseEntity.ok().body(new ApiResponseModel("book updated successfully", true, book));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseModel("Error updating book: " + e.getMessage(), false, null));
        }
    }

    public boolean validateRequestBody(Map<String, Object> request_body) {
        // 1 - check if request body is empty
        if (request_body.isEmpty()) {
            return false;
        }
        // 2 - check if request body has all required fields
        if (!request_body.containsKey("book_title") || !request_body.containsKey("book_description")
                || !request_body.containsKey("book_image") || !request_body.containsKey("author_id")) {
            return false;
        }
        // 3 - check if request body fields are empty
        if (request_body.get("book_title").toString().isEmpty()
                || request_body.get("book_description").toString().isEmpty()
                || request_body.get("book_image").toString().isEmpty()
                || request_body.get("author_id").toString().isEmpty()) {
            return false;
        }
        // 4 - check if author_id is a number
        try {
            Integer.parseInt(request_body.get("author_id").toString());
        } catch (Exception e) {
            return false;
        }
        // 5 - check if author_id is a valid id
        Author author = author_service.geAuthor(Integer.parseInt(request_body.get("author_id").toString()));
        if (author == null) {
            return false;
        }
        // 6 - return true if all checks passed
        return true;
    }

}
