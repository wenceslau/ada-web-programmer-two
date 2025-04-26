package com.ada.hibernate.controllers;

import com.ada.hibernate.entities.Book;
import com.ada.hibernate.repositories.BookDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;

        // Initialize with some sample data
       // bookRepository.save(new Book("Book Title 1", "Author 1","1234567890"));
       // bookRepository.save(new Book("Book Title 2", "Author 2","0987654321"));

    }

    @GetMapping
    public List<Book> getBooks() {
        return bookDAO.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        bookDAO.saveBook(book);
        return book;
    }

    @PutMapping("/{isbn}")
    public Book editBook(@PathVariable String isbn, @RequestBody Book book) {
        Book existingBook = bookDAO.getBookByIsbn(isbn);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        bookDAO.updateBook(existingBook);

        return existingBook; // or throw an exception
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        Book existingBook = bookDAO.getBookByIsbn(isbn);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        bookDAO.deleteBook(existingBook.getId());
    }
}

/*
    http://localhost:8080/swagger-ui/index.html

 */
