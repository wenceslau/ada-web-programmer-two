package br.com.ada.springhibernate.repositories;

import br.com.ada.springhibernate.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDAO {

    private final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(book);
        tx.commit();
        session.close();
    }

    public Book getBookById(Long id) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;
    }

    public void updateBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(book);
        tx.commit();
        session.close();
    }

    public void deleteBook(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            session.delete(book);
        }
        tx.commit();
        session.close();
    }

    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        List<Book> books = session
                .createQuery("from Book", Book.class)
                .list();
        session.close();
        return books;
    }

    public Book getBookByIsbn(String isbn) {
        Session session = sessionFactory.openSession();
        Book book = session
                .createQuery("from Book where isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .uniqueResult();
        session.close();
        return book;
    }

}
