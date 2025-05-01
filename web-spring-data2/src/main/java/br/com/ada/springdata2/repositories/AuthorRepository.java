package br.com.ada.springdata2.repositories;

import br.com.ada.springdata2.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {




}
