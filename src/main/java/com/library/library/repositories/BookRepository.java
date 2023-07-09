package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    
}
