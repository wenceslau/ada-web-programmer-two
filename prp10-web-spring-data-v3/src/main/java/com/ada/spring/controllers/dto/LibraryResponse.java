package com.ada.spring.controllers.dto;

import com.ada.spring.entities.Library;

import java.util.List;

public record LibraryResponse(Long id,
                              String name) {

    public static LibraryResponse fromLibrary(Library library) {
        return new LibraryResponse(
                library.getId(),
                library.getName());
    }
}
