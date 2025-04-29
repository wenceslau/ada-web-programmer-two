package br.com.ada.springdata4.controllers.dto;

import br.com.ada.springdata4.entities.Author;

public record AuthorResponse(
        String name,
        String email) {

    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponse(author.getName(), author.getEmail());
    }
}
