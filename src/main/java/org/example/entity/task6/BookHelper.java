package org.example.entity.task6;

import org.hibernate.Session;

import java.util.List;

public class BookHelper {
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
