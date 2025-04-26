package ada.web.spring.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    public static final List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book("O Senhor dos Anéis", "J.R.R. Tolkien", "978-3-16-148410-0"));
        bookList.add(new Book("1984", "George Orwell", "978-0-452-28423-4"));
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookList;
    }

    @PostMapping
    public Book addBook(@RequestBody @Valid Book book) {
        bookList.add(book);
        return book;
    }

    @PutMapping("/{isbn}")
    public Book editBook(@PathVariable String isbn, @RequestBody @Valid Book book) {
        Book existingBook = bookList.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        return existingBook; // or throw an exception
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        Book existingBook = bookList.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (existingBook == null) {
            throw new RuntimeException("Book not found");
        }

        bookList.remove(existingBook);
    }
}

/*
    http://localhost:8080/swagger-ui/index.html
 */
