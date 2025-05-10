package ada.web.springcache.controllers;

import ada.web.springcache.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book = bookService.addBook(book);
        bookService.cleanerCache();
        return book;
    }

}

/*
    http://localhost:8080/swagger-ui/index.html
 */
