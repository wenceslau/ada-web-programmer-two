package br.com.ada.springdatamanytoone.repositories.objects;

public record BookDetails(
        Long id,
        String title,
        Long authorId,
        String authorName,
        String authorEmail,
        String isbn) {
}
