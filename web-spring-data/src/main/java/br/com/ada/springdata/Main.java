package br.com.ada.springdata;

import br.com.ada.springdata.entities.Book;
import br.com.ada.springdata.repositories.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    public ApplicationRunner run(BookRepository bookRepository) {
        return (arguments) -> {
            System.out.println("Initializing database with sample data...");
            bookRepository.save(new Book("O Senhor dos Anéis", "J.R.R. Tolkien", "12345"));
            bookRepository.save(new Book("1984", "George Orwell", "54321"));

            List<Book> orwell = bookRepository.findAllByAuthorContainingIgnoreCase("orwell");
            System.out.println("Books by George Orwell:");
            for (Book book : orwell) {
                System.out.println(" - " + book.getTitle());
            }
        };
    }

    /*
        Important information:

        http://localhost:8080/swagger-ui/index.html

    */
}
