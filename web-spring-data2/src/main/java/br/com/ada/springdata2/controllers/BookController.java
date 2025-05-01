package br.com.ada.springdata2.controllers;

import br.com.ada.springdata2.controllers.dto.BookRequest;
import br.com.ada.springdata2.controllers.dto.BookResponse;
import br.com.ada.springdata2.entities.Book;
import br.com.ada.springdata2.repositories.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

        // Initialize with some sample data
       // bookRepository.save(new Book("Book Title 1", "Author 1","1234567890"));
       // bookRepository.save(new Book("Book Title 2", "Author 2","0987654321"));

    }

    @GetMapping
    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(BookResponse::fromBook)
                .toList();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        return ResponseEntity
                .ok(BookResponse.fromBook(book));
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest) {
        Book bookAdded =  bookRepository.save(bookRequest.toBook());
        return ResponseEntity
                .ok(BookResponse.fromBook(bookAdded));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<?> editBook(@PathVariable String isbn, @RequestBody BookRequest bookRequest) {
        Book bookFound = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (bookFound == null) {
            throw new RuntimeException("Book not found");
        }

        bookFound.setTitle(bookRequest.title());
        bookFound.setIsbn(bookRequest.isbn());

        bookFound.getAuthor().setName(bookRequest.authorName());
        bookFound.getAuthor().setEmail(bookRequest.authorEmail());

        bookRepository.save(bookFound);

        return ResponseEntity
                .ok("Book updated successfully");
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<?> patchBook(@PathVariable String isbn, @RequestBody BookRequest bookRequest) {
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

        bookRepository.save(bookFound);

        return ResponseEntity
                .ok("Book updated successfully");
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book bookFound = bookRepository.findByIsbn(isbn)
                .orElse(null);

        if (bookFound == null) {
            throw new RuntimeException("Book not found");
        }

        bookRepository.delete(bookFound);

        return ResponseEntity
                .noContent()
                .build();
    }

}

/*
    http://localhost:8080/swagger-ui/index.html

 */
