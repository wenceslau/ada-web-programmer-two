package br.com.ada.springdatamanytoone.repositories;

import br.com.ada.springdatamanytoone.entities.Book;
import br.com.ada.springdatamanytoone.repositories.objects.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    //_________________________________________________________________________________________________________________

    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Optional<Book> searchByIsbn(String isbn);

    @NativeQuery(value = "SELECT * FROM books WHERE isbn = :isbn")
    Optional<Book> searchByIsbnUsingNativeQuery(String isbn);



    //_________________________________________________________________________________________________________________


    List<Book> findAllByTitleContaining(String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> searchAllByTitleLike(String title);

    @NativeQuery(value = "SELECT * FROM books WHERE title LIKE %:title%")
    List<Book> searchAllByTitleLikeUsingNativeQuery(String title);


    //_________________________________________________________________________________________________________________


    List<Book> findAllByAuthorNameContainingIgnoreCase(String authorName);

    @Query("SELECT b FROM Book b WHERE lower(b.author.name) LIKE lower('%:authorName%')")
    List<Book> buscaAllByAuthorNameLike(String authorName);

    @NativeQuery(value = "SELECT * FROM books WHERE author_id IN (SELECT id FROM authors WHERE name LIKE %:author%)")
    List<Book> searchAllByAuthorLikeUsingNativeQuerySubselect(String author);

    @NativeQuery(value = "SELECT b.* FROM books b JOIN authors a ON b.author_id = a.id WHERE a.name LIKE %:author%")
    List<Book> searchAllByAuthorLikeUsingNativeQueryJoin(String author);


    //_________________________________________________________________________________________________________________


    @Query("SELECT " +
           "new br.com.ada.springdatamanytoone.repositories.objects.BookDetails(b.id, b.title, a.id, a.name, a.email, b.isbn) " +
           "FROM Book b " +
           "JOIN b.author a")
    List<BookDetails> listAllBookWithDetails();

    @NativeQuery(value = """
            SELECT b.id, b.title, a.id AS author_id, a.name AS author_name, a.email AS author_email, b.isbn
            FROM books b
            JOIN authors a ON b.author_id = a.id
            WHERE b.isbn = :isbn
            """)
    Optional<BookDetails> searchByIsbnWithAuthorDetails(String isbn);


}
