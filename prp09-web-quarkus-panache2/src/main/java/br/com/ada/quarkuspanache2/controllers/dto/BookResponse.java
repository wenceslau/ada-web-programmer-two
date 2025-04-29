package br.com.ada.quarkuspanache2.controllers.dto;

import br.com.ada.quarkuspanache2.entities.Book;

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
