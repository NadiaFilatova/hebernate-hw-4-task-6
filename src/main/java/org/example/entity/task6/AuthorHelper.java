package org.example.entity.task6;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class AuthorHelper {
    public void updateName(String name, Long authorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Author author = session.get(Author.class, authorId);
        author.setName(name);

        session.persist(author);
        session.getTransaction().commit();
        session.close();
    }

    public List<Author> getAuthorList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);
        cq.select(root);

        Query query = session.createQuery(cq);
        List<Author> authorList = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Author author = session.get(Author.class, id);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    public void saveAuthor(Author author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(author);
        session.getTransaction().commit();
        session.close();
    }

    public void saveAuthors(List<Author> authors) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (int i = 0; i < authors.size(); i++) {
            session.persist(authors.get(i));
            if (i % 10 == 0) {
                session.flush();
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public void updateByLastNameLengthGreaterThan(int length) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery("UPDATE Author set name = '1' where length(lastName)>:targetLength");
        query.setParameter("targetLength", length).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
