package br.com.ada.springdata4.controllers.dto;


import br.com.ada.springdata4.entities.Author;
import br.com.ada.springdata4.entities.Book;

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
