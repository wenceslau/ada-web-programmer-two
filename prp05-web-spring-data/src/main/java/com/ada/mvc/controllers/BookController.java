package com.ada.mvc.controllers;

import com.ada.mvc.entities.Book;
import com.ada.mvc.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    @PutMapping("/{isbn}")
    public Book editBook(@PathVariable String isbn, @RequestBody Book book) {
        Book existingBook = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        bookRepository.save(existingBook);

        return existingBook; // or throw an exception
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        Book existingBook = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        bookRepository.delete(existingBook);
    }
}


