package com.ada.panache.controllers.dto;


import com.ada.panache.entities.Author;

public record AuthorResponse(
        String name,
        String email) {

    public static AuthorResponse fromAuthor(Author author) {
        return new AuthorResponse(author.getName(), author.getEmail());
    }
}
