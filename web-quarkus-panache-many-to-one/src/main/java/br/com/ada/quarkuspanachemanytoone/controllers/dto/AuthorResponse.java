package br.com.ada.quarkuspanachemanytoone.controllers.dto;


import br.com.ada.quarkuspanachemanytoone.entities.Author;

public record AuthorResponse(
        String name,
        String email) {

    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponse(author.getName(), author.getEmail());
    }
}
