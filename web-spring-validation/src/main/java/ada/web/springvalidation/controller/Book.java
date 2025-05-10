package ada.web.springvalidation.controller;

import ada.web.springvalidation.annotation.UniqueISBN;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Book {

    @NotBlank(message = "O nome é obrigatório")
    private String title;
    @NotBlank
    private String author;
    @Size(min = 2, max = 5) @UniqueISBN
    private String isbn;

    public Book() {
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
