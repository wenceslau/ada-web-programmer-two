package br.com.ada.springdata;

import br.com.ada.springdata.entities.Book;
import br.com.ada.springdata.repositories.BookRepository;
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
            bookRepository.save(new Book("O Senhor dos Anéis", "J.R.R. Tolkien", "978-3-16-148410-0"));
            bookRepository.save(new Book("1984", "George Orwell", "978-0-452-28423-4"));

        };
    }

    /*
        Important information:

        http://localhost:8080/swagger-ui/index.html

    */
}
