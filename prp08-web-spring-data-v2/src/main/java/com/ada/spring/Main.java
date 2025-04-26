package com.ada.spring;

import com.ada.spring.entities.Author;
import com.ada.spring.entities.Book;
import com.ada.spring.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Runnable run(BookRepository bookRepository) {
        return () -> {
            System.out.println("Initializing database with sample data...");

            Author author1 = new Author("J.R.R. Tolkien", "jrr@email.com");
            Author author2 = new Author("George Orwell", "go@email.com");

            bookRepository.save(new Book("O Senhor dos Anéis", "978-3-16-148410-0", author1));
            bookRepository.save(new Book("The Hobbit", "978-0-451-52695-7", author1));
            bookRepository.save(new Book("1984", "978-0-452-28423-4", author2));
        };
    }
}
