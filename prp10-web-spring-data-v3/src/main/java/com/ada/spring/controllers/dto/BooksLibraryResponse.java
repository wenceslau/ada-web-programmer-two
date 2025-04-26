package com.ada.spring.controllers.dto;

import com.ada.spring.entities.Library;

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
