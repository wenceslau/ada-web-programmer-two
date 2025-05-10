package br.com.ada.springdatamanytoone.controllers.dto;

import br.com.ada.springdatamanytoone.entities.Author;
import br.com.ada.springdatamanytoone.entities.Book;

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
