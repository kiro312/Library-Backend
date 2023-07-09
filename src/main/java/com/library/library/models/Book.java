package com.library.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String book_title;
    private String book_description;
    private String book_image;
    
    @ManyToOne(targetEntity = Author.class, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author book_author;

    public Book(int id, String title, String description, String image, Author author) {
        this.id = id;
        this.book_title = title;
        this.book_description = description;
        this.book_image = image;
        this.book_author = author;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return book_title;
    }

    public void setBookTitle(String title) {
        this.book_title = title;
    }

    public String getBookDescription() {
        return book_description;
    }

    public void setBookDescription(String description) {
        this.book_description = description;
    }

    public String getBookImage() {
        return book_image;
    }

    public void setBookImage(String image) {
        this.book_image = image;
    }

    public Author getBookAuthor() {
        return book_author;
    }

    public void setBookAuthor(Author author) {
        this.book_author = author;
    }


}
