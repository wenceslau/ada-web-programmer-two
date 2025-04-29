package br.com.ada.springdata4;

import br.com.ada.springdata4.entities.Author;
import br.com.ada.springdata4.entities.Book;
import ada.web.spring.entities.Library;
import br.com.ada.springdata4.repositories.BookRepository;
import ada.web.spring.repositories.LibraryRepository;
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
    public ApplicationRunner run(BookRepository bookRepository, LibraryRepository libraryRepository) {
        return args -> {
            System.out.println("Initializing database with sample data...");

            Author author1 = new Author("J.R.R. Tolkien", "jrr@email.com");
            Author author2 = new Author("George Orwell", "go@email.com");

            Book book1 = new Book("O Senhor dos Anéis", "12345", author1);
            Book book2 = new Book("1984", "12347", author2);

            bookRepository.save(book1);
            bookRepository.save(book2);

            Library library1 = new Library("Central Library");
            library1.addBook(book1);
            library1.addBook(book2);
            libraryRepository.save(library1);

            var libraries = libraryRepository.findAll();
            System.out.println("Libraries:" + libraries);

        };
    }
}
