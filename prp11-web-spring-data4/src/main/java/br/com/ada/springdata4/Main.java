package br.com.ada.springdata4;

import br.com.ada.springdata4.entities.Course;
import br.com.ada.springdata4.entities.Student;
import br.com.ada.springdata4.repositories.CourseRepository;
import br.com.ada.springdata4.repositories.StudentRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    /*
        Type 1 Course has students. Create students, then create courses and add students to courses.
     */
//    @Bean
//    public ApplicationRunner run1(StudentRepository studentRepository, CourseRepository courseRepository) {
//        return (arguments) -> {
//            System.out.println("Initializing database with sample data run 1...");
//
//            // Create sample students and courses
//            Student student1 = new Student().setName("Alice");
//            Student student2 = new Student().setName("Bob");
//            studentRepository.save(student1);
//            studentRepository.save(student2);
//
//            Course course1 = new Course().setTitle("Mathematics");
//            course1.addStudent(student1);
//            course1.addStudent(student2);
//            courseRepository.save(course1);
//
//            Course course2 = new Course().setTitle("Science");
//            course2.addStudent(student1);
//            courseRepository.save(course2);
//
//            System.out.println("Sample data initialized.");
//        };
//    }

    /*
        Type 2 Students has courses. Create courses, then create students and add courses to students.
     */

    @Bean
    public ApplicationRunner run2(StudentRepository studentRepository, CourseRepository courseRepository) {
        return (arguments) -> {
            System.out.println("Initializing database with sample data run2...");

            // Course has students

            Course course1 = new Course().setTitle("Mathematics");
            Course course2 = new Course().setTitle("Science");
            courseRepository.save(course1);
            courseRepository.save(course2);

            // Create sample students and courses
            Student student1 = new Student().setName("Alice");
            student1.addCourse(course1);
            student1.addCourse(course2);
            studentRepository.save(student1);

            Student student2 = new Student().setName("Bob");
            student2.addCourse(course1);
            studentRepository.save(student2);

            System.out.println("Sample data initialized.");
        };
    }
}
