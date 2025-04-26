package ada.web.spring.services;

import ada.web.spring.controllers.Book;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book("O Senhor dos Anéis", "J.R.R. Tolkien", "978-3-16-148410-0"));
        bookList.add(new Book("1984", "George Orwell", "978-0-452-28423-4"));
    }

    @Cacheable("books")
    public List<Book> getBooks() {
        System.out.println("Fetching books from the database...");
        return bookList;
    }

    public Book addBook(Book book) {
        bookList.add(book);
        return book;
    }

    @CacheEvict(value = "books")
    public void cleanerCache() {
        System.out.println("cache cleaned!");
    }


}
