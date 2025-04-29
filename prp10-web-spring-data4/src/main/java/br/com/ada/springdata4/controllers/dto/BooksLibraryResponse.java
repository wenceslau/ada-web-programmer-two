package br.com.ada.springdata4.controllers.dto;


import ada.web.spring.entities.Library;

import java.util.List;

public record BooksLibraryResponse(Long id,
                                   String name,
                                   List<BookResponse> books) {

    public static BooksLibraryResponse fromLibrary(Library library) {
        return new BooksLibraryResponse(
                library.getId(),
                library.getName(),
                library.getBooks().stream()
                        .map(BookResponse::fromBook)
                        .toList());
    }
}
