package com.ada.panache.controllers.dto;


import com.ada.panache.entities.Author;
import com.ada.panache.entities.Book;

public record BookRequest(
        String title,
        String isbn,
        String authorName,
        String authorEmail) {

    public Book toBook() {
        Author author = new Author(authorName, authorEmail);
        return new Book(title, isbn, author );
    }
}
