package com.library.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    
}
