package br.com.ada.springdata4.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String title;


    //_____________________________________________________________________

    // Type 1 Course has students. Create students, then create courses and add students to courses.
//        @ManyToMany
//        @JoinTable(
//                name = "course_student",
//                joinColumns = @JoinColumn(name = "course_id"),
//                inverseJoinColumns = @JoinColumn(name = "student_id")
//        )
//        private Set<Student> students = new HashSet<>();


    // Type 2 Students has courses. Create courses, then create students and add courses to students.
        @ManyToMany(mappedBy = "courses")
        private Set<Student> students = new HashSet<>();

    //_____________________________________________________________________


    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Course setStudents(Set<Student> students) {
        this.students = students;
        return this;
    }

    // Used on for type 1
    public Course addStudent(Student student) {
        this.students.add(student);
        return this;
    }
}
