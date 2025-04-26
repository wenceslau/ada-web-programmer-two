package com.ada.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private static final List<Book> books = new ArrayList<>();

    static {
        books.add(new Book("1984", "George Orwell", "1234567890"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "0987654321"));
    }

    @GetMapping("/book")
    public String getBooks(Model model) {
        model.addAttribute("books", books);
        return "bookList";  // Nome do templates HTML
    }

}

