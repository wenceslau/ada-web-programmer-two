package br.com.ada.springdatamanytoone.repositories;

import br.com.ada.springdatamanytoone.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {




}
