package br.com.ada.quarkuspanache.repositories;

import br.com.ada.quarkuspanache.entities.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;


@ApplicationScoped
public class BookRepository implements PanacheRepositoryBase<Book, Long> {

    public Optional<Book> findByIsbn(String isbn) {
        return find("isbn", isbn)
                .firstResultOptional();
    }

}
