package br.com.ada.springdata4.repositories;

import br.com.ada.springdata4.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
