package br.com.ada.springdata2;

import br.com.ada.springdata2.entities.Author;
import br.com.ada.springdata2.entities.Book;
import br.com.ada.springdata2.repositories.AuthorRepository;
import br.com.ada.springdata2.repositories.BookRepository;
import br.com.ada.springdata2.repositories.objects.BookDetails;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ApplicationRunner run(BookRepository bookRepository, AuthorRepository authorRepository) {
        return (arguments) -> {
            System.out.println("Initializing database with sample data...");

            Author author1 = new Author("J.R.R. Tolkien", "jrr@email.com");
            Author author2 = new Author("George Orwell", "go@email.com");
            authorRepository.save(author1);
            authorRepository.save(author2);

            bookRepository.save(new Book("O Senhor dos Anéis", "12345", author1));
            bookRepository.save(new Book("The Hobbit", "12346", author1));
            bookRepository.save(new Book("1984", "12347", author2));

            Optional<BookDetails> bookDetails = bookRepository.searchByIsbnWithAuthorDetails("12345");

            bookDetails.ifPresent(details -> {
                System.out.println("Book Details:");
                System.out.println(" - ID: " + details.id());
                System.out.println(" - Title: " + details.title());
                System.out.println(" - Author: " + details.authorName());
                System.out.println(" - Author Email: " + details.authorEmail());
                System.out.println(" - ISBN: " + details.isbn());
            });

            List<BookDetails> books = bookRepository.listAllBookWithDetails();

            System.out.println("All Books with Details:");
            for (BookDetails book : books) {
                System.out.println(" - ID: " + book.id());
                System.out.println(" - Title: " + book.title());
                System.out.println(" - Author: " + book.authorName());
                System.out.println(" - Author Email: " + book.authorEmail());
                System.out.println(" - ISBN: " + book.isbn());
            }

        };
    }
}
