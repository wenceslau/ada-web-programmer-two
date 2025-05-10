package br.com.ada.quarkuspanachemanytoone.controllers.dto;

import br.com.ada.quarkuspanachemanytoone.entities.Book;

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
