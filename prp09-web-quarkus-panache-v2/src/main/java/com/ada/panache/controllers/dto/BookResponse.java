package com.ada.panache.controllers.dto;

import com.ada.panache.entities.Book;

public record BookResponse(
        Long id,
        String title,
        String isbn,
        AuthorResponse author) {

    public static BookResponse fromBook(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                AuthorResponse.fromAuthor(book.getAuthor()));
    }
}
