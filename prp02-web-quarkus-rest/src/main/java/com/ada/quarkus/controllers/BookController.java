package com.ada.quarkus.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/book")
public class BookController {

    private static final List<Book> books = new ArrayList<>();

    static {
        books.add(new Book("1984", "George Orwell", "1234567890"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "0987654321"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return books;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {

        books.add(book);

        return Response
                .status(Response.Status.CREATED)
                .entity(book).build();
    }

    @PUT
    @Path("/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book book) {
        Book existingBook = books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        return Response
                .ok(existingBook)
                .build();
    }

    @DELETE
    @Path("/{isbn}")
    public Response deleteBook(@PathParam("isbn") String isbn) {
        Book existingBook = books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        books.remove(existingBook);
        return Response
                .noContent()
                .build();
    }
}
