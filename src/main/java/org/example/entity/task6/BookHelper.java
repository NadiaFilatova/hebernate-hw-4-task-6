package org.example.entity.task6;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookHelper {
    public void updateNameById(String newName, Long bookId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Book book = session.get(Book.class, bookId);
        book.setName(newName);

        session.persist(book);
        session.getTransaction().commit();
        session.close();
    }
    public List<Book> getByAuthorName(String authorName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Book> query = session.createQuery(
                "select book from Book book where book.author.name = :authorName", Book.class
        );
        List<Book> results = query.setParameter("authorName", authorName).getResultList();

        session.getTransaction().commit();
        session.close();
        return results;
    }

    public Book getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    public List<Book> getBooks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Book> books = session.createQuery("from Book", Book.class).list();
        session.getTransaction().commit();
        session.close();
        return books;
    }

    public void createBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(book);
        session.getTransaction().commit();
        session.close();
    }
}
