package br.com.ada.springhibernate;

import br.com.ada.springhibernate.entities.Book;
import br.com.ada.springhibernate.repositories.BookDAO;
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
    public ApplicationRunner run(BookDAO bookDAO) {
        return (arguments) -> {
            System.out.println("Initializing database with sample data...");
            bookDAO.saveBook(new Book("O Senhor dos Anéis", "J.R.R. Tolkien", "978-3-16-148410-0"));
            bookDAO.saveBook(new Book("1984", "George Orwell", "978-0-452-28423-4"));

        };
    }
}
