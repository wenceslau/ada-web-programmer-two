package br.com.ada.springdatamanytoone.controllers.dto;

import br.com.ada.springdatamanytoone.entities.Author;

public record AuthorResponse(
        String name,
        String email) {

    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponse(author.getName(), author.getEmail());
    }
}
