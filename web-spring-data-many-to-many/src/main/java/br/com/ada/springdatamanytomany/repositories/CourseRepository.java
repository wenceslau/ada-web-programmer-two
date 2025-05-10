package br.com.ada.springdatamanytomany.repositories;

import br.com.ada.springdatamanytomany.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
