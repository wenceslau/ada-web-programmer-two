package br.com.ada.springdatasimplefywaydb.repositories;

import br.com.ada.springdatasimplefywaydb.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAllByTitleContaining(String title);

    List<Book> findAllByAuthorContainingIgnoreCase(String author);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Optional<Book> searchByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> searchAllByTitleLike(String title);

    @Query("SELECT b FROM Book b WHERE b.author LIKE %:author%")
    List<Book> searchAllByAuthorLike(String author);

    @NativeQuery(value = "SELECT * FROM books WHERE isbn = :isbn")
    Optional<Book> searchByIsbnUsingNativeQuery(String isbn);

    @NativeQuery(value = "SELECT * FROM books WHERE title LIKE %:title%")
    List<Book> searchAllByTitleLikeUsingNativeQuery(String title);

    @NativeQuery(value = "SELECT * FROM books WHERE author LIKE %:author%")
    List<Book> searchAllByAuthorLikeUsingNativeQuery(String author);

}
