package br.com.ada.springdata3.repositories;

import br.com.ada.springdata3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
