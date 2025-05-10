package br.com.ada.springdatamanytomany.repositories;

import br.com.ada.springdatamanytomany.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
