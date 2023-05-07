package org.example.entity.task6;

import java.util.List;

import static org.example.entity.task6.HibernateUtil.doInTransaction;

public class BookService {
    public void updateNameById(String newName, Long bookId) {
        doInTransaction(session -> {
            Book book = session.get(Book.class, bookId);
            book.setName(newName);
            session.persist(book);
        });
    }

    public Book getById(long id) {
        return doInTransaction(session -> {
            return session.get(Book.class, id);
        });
    }

    public List<Book> getBooks() {
        return doInTransaction(session -> {
            return session.createQuery("from Book", Book.class).list();
        });
    }

    public void createBook(Book book) {
        doInTransaction(session -> {
            session.persist(book);
        });
    }

    public void deleteById(Long bookId) {
        doInTransaction(session -> {
            Book book = session.get(Book.class, bookId);
            session.remove(book);
        });
    }

    public void deleteByAuthorName(String authorName) {
        doInTransaction(session -> {
            session
                    .createQuery("DELETE Book WHERE name =:name ", Book.class)
                    .setParameter("name", authorName)
                    .executeUpdate();
        });
    }
}
