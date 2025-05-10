package br.com.ada.springdataonetomany.repositories;

import br.com.ada.springdataonetomany.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
