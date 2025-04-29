package br.com.ada.quarkuspanache2.controllers;

import br.com.ada.quarkuspanache2.controllers.dto.BookRequest;
import br.com.ada.quarkuspanache2.controllers.dto.BookResponse;
import br.com.ada.quarkuspanache2.entities.Book;
import br.com.ada.quarkuspanache2.repositories.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.listAll();

        return books.stream()
                .map(BookResponse::fromBook)
                .toList();
    }

    @GET
    @Path("/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")  String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        return Response
                .ok(BookResponse.fromBook(book))
                .build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(BookRequest bookRequest) {

        Book bookToAdd = bookRequest.toBook();

        bookRepository.persist(bookToAdd);

        return Response
                .status(Response.Status.CREATED)
                .entity(BookResponse.fromBook(bookToAdd))
                .build();
    }

    @PUT
    @Transactional
    @Path("/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, BookRequest bookRequest) {
        Book bookFound = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (bookFound == null) {
            throw new RuntimeException("Book not found");
        }

        bookFound.setTitle(bookRequest.title());
        bookFound.setIsbn(bookRequest.isbn());

        bookFound.getAuthor().setName(bookRequest.authorName());
        bookFound.getAuthor().setEmail(bookRequest.authorEmail());

        bookRepository.persist(bookFound);

        return Response
                .ok("Book updated successfully")
                .build();
    }

    @PATCH
    @Transactional
    @Path("/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response patchBook(@PathParam("isbn") String isbn,BookRequest bookRequest) {
        Book bookFound = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (bookFound == null) {
            throw new RuntimeException("Book not found");
        }

        if (bookRequest.title() != null) {
            bookFound.setTitle(bookRequest.title());
        }
        if (bookRequest.isbn() != null) {
            bookFound.setIsbn(bookRequest.isbn());
        }
        if (bookRequest.authorName() != null) {
            bookFound.getAuthor().setName(bookRequest.authorName());
        }
        if (bookRequest.authorEmail() != null) {
            bookFound.getAuthor().setEmail(bookRequest.authorEmail());
        }

        bookRepository.persist(bookFound);

        return Response
                .ok("Book updated successfully")
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
