package br.com.ada.springdatasimplefywaydb.controllers;

import br.com.ada.springdatasimplefywaydb.entities.Book;
import br.com.ada.springdatasimplefywaydb.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

//    @GetMapping
//    public List<Book> getBooks() {
//        return bookRepository.findAll();
//    }

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


