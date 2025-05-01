package br.com.ada.springdata4.repositories;

import br.com.ada.springdata4.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
