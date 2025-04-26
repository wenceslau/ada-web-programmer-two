package com.ada.spring.controllers;

import com.ada.spring.controllers.dto.BooksLibraryResponse;
import com.ada.spring.controllers.dto.LibraryResponse;
import com.ada.spring.entities.Book;
import com.ada.spring.entities.Library;
import com.ada.spring.repositories.BookRepository;
import com.ada.spring.repositories.LibraryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public LibraryController(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;

        // Initialize with some sample data
        // bookRepository.save(new Book("Book Title 1", "Author 1","1234567890"));
        // bookRepository.save(new Book("Book Title 2", "Author 2","0987654321"));
        this.bookRepository = bookRepository;
    }


    @GetMapping("/{id}/books")
    public ResponseEntity<?> getLibraryBooks(@PathVariable Long id) {
        Library library = libraryRepository.findById(id)
                .orElse(null);

        if (library == null) {
            throw new RuntimeException("Library not found");
        }

        return ResponseEntity
                .ok(BooksLibraryResponse.fromLibrary(library));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLibrary(@PathVariable Long id) {
        Library library = libraryRepository.findById(id)
                .orElse(null);

        if (library == null) {
            throw new RuntimeException("Library not found");
        }

        return ResponseEntity
                .ok(LibraryResponse.fromLibrary(library));
    }

    @PostMapping("/{id}/{isbn}")
    public ResponseEntity<?> addBook(@PathVariable Long id, @PathVariable String isbn) {
        Library library = libraryRepository.findById(id)
                .orElse(null);

        if (library == null) {
            throw new RuntimeException("Library not found");
        }

        Book book = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (book == null) {
            throw new RuntimeException("Book does not exists");
        }
        library.addBook(book);
        libraryRepository.save(library);

        return ResponseEntity
                .ok("Book added to library");
    }

}

/*
    http://localhost:8080/swagger-ui/index.html

 */
