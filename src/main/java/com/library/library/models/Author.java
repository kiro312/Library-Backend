package com.library.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author_name;
    private String author_email;
    private String author_bio;
    
    public Author(int id, String author_name, String author_email, String author_bio) {
        this.id = id;
        this.author_name = author_name;
        this.author_email = author_email;
        this.author_bio = author_bio;
    }

    public Author() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return author_name;
    }

    public void setAuthorName(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthorEmail() {
        return author_email;
    }

    public void setAuthorEmail(String author_email) {
        this.author_email = author_email;
    }

    public String getAuthorBio() {
        return author_bio;
    }

    public void setAuthorBio(String author_bio) {
        this.author_bio = author_bio;
    }

}
