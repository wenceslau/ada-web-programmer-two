package br.com.ada.springdata4.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    //_____________________________________________________________________

    // Type 1 Course has students. Create students, then create courses and add students to courses.

//    @ManyToMany(mappedBy = "students")
//    private Set<Course> courses = new HashSet<>();

    //_____________________________________________________________________


    // Type 2 Students has courses. Create courses, then create students and add courses to students.

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Student setCourses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    // Used on for type 2
    public Student addCourse(Course course) {
        this.courses.add(course);
        return this;
    }
}
