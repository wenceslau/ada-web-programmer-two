package br.com.ada.springdatasimple;

import br.com.ada.springdatasimple.entities.Book;
import br.com.ada.springdatasimple.repositories.BookRepository;
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
            bookRepository.save(new Book("A Revolução dos Bichos", "George Orwell", "67890"));
            bookRepository.save(new Book("O Hobbit", "J.R.R. Tolkien", "98765"));
            bookRepository.save(new Book("Dom Casmurro", "Machado de Assis", "13579"));
            bookRepository.save(new Book("Memórias Póstumas de Brás Cubas", "Machado de Assis", "24680"));
            bookRepository.save(new Book("O Guarani", "José de Alencar", "11223"));
            bookRepository.save(new Book("Iracema", "José de Alencar", "33445"));
            bookRepository.save(new Book("Senhora", "José de Alencar", "55667"));

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
