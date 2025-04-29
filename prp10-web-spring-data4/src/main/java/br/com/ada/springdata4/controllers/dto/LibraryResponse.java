package br.com.ada.springdata4.controllers.dto;

import ada.web.spring.entities.Library;

public record LibraryResponse(Long id,
                              String name) {

    public static LibraryResponse fromLibrary(Library library) {
        return new LibraryResponse(
                library.getId(),
                library.getName());
    }
}
