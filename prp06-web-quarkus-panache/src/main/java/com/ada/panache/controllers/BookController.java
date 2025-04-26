package com.ada.panache.controllers;

import com.ada.panache.entities.Book;
import com.ada.panache.repositories.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return bookRepository.listAll();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {

        bookRepository.persist(book);

        return Response
                .status(Response.Status.CREATED)
                .entity(book)
                .build();
    }

    @PUT
    @Transactional
    @Path("/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book book) {
        Book existingBook = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        bookRepository.persist(existingBook);

        return Response
                .ok(existingBook)
                .build();
    }

    @DELETE
    @Transactional
    @Path("/{isbn}")
    public Response deleteBook(@PathParam("isbn") String isbn) {
        Book existingBook = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        bookRepository.delete(existingBook);

        return Response
                .noContent()
                .build();
    }
}
