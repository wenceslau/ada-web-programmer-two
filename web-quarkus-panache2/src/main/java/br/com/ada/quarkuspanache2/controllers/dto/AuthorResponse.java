package br.com.ada.quarkuspanache2.controllers.dto;


import br.com.ada.quarkuspanache2.entities.Author;

public record AuthorResponse(
        String name,
        String email) {

    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponse(author.getName(), author.getEmail());
    }
}
