package br.com.ada.quarkuspanachemanytoone.controllers.dto;


import br.com.ada.quarkuspanachemanytoone.entities.Author;
import br.com.ada.quarkuspanachemanytoone.entities.Book;

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
