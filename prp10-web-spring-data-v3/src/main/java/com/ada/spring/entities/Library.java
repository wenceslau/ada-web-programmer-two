package com.ada.spring.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libraries")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Library() {
    }

    public Library(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Library setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Library setName(String name) {
        this.name = name;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Library setBooks(List<Book> books) {
        this.books = books;
        return this;
    }

    public void addBook(Book book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        book.setLibrary(this);
        books.add(book);
    }
}
