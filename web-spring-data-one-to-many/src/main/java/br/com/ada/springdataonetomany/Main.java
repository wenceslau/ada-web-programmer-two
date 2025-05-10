package br.com.ada.springdataonetomany;

import br.com.ada.springdataonetomany.entities.Author;
import br.com.ada.springdataonetomany.entities.Book;
import br.com.ada.springdataonetomany.repositories.AuthorRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ApplicationRunner run(AuthorRepository authorRepository) {
        return (arguments) -> {
            System.out.println("Initializing database with sample data...");

            Author author2 = new Author("George Orwell", "go@email.com");

            Book book1 = new Book("1984", "12345");
            Book book2 = new Book("The Great Gatsby", "12346");

            author2.addBook(book1);
            author2.addBook(book2);

            authorRepository.save(author2);
        };
    }
}
