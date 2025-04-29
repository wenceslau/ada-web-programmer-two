package br.com.ada.springdata4.controllers.dto;


import br.com.ada.springdata4.entities.Book;

public record BookResponse(
        String title,
        String isbn,
        AuthorResponse author) {

    public static BookResponse fromBook(Book book) {
        return new BookResponse(
                book.getTitle(),
                book.getIsbn(),
                AuthorResponse.fromAuthor(book.getAuthor()));
    }
}
