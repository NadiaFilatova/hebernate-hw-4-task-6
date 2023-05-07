package org.example.entity.task6;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

import static org.example.entity.task6.HibernateUtil.doInTransaction;

public class AuthorService {
    public void updateName(String name, Long authorId) {
        doInTransaction(session -> {
            Author author = session.get(Author.class, authorId);
            author.setName(name);
            session.persist(author);
        });
    }

    public List<Author> getAuthorList() {
        return doInTransaction(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Author> cq = cb.createQuery(Author.class);
            Root<Author> root = cq.from(Author.class);

            cq.select(root);
            return session.createQuery(cq).getResultList();
        });
    }

    public Author getAuthorById(long id) {
        return doInTransaction(session -> {
            return session.get(Author.class, id);
        });
    }

    public void saveAuthor(Author author) {
        doInTransaction(session -> {
            session.persist(author);
        });
    }

    public void saveAuthors(List<Author> authors) {
        doInTransaction(session -> {
            for (int i = 0; i < authors.size(); i++) {
                session.persist(authors.get(i));
                if (i % 10 == 0) {
                    session.flush();
                }
            }
        });
    }

    public void updateByLastNameLengthGreaterThan(int length) {
        doInTransaction(session -> {
            session
                    .createQuery("UPDATE Author set name = '1' where length(lastName) > :targetLength", Author.class)
                    .setParameter("targetLength", length)
                    .executeUpdate();
        });
    }

    public List<Author> findByFirstNameOrLastNameLike(String name) {
        return doInTransaction(session -> {
            return session
                    .createQuery("SELECT author FROM Author author WHERE name like :keyword or lastName like :keyword", Author.class) // HQL - Hibernate Query Language
                    .setParameter("keyword", "%" + name + "%")
                    .list();
        });
    }
  //  в пакете ex_003_delete методы createCriteria и createCriteriaLogic переписать правильно.
    // немає там тих методів,  проте, є такі:
    public void deleteCriteria() {
        doInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Author> criteriaDelete = criteriaBuilder.createCriteriaDelete(Author.class);

            criteriaDelete.where(criteriaBuilder.like(criteriaDelete.from(Author.class).get("name"), "%1%"));
            session.createMutationQuery(criteriaDelete).executeUpdate();
        });
    }

    public void deleteCriteriaLogic() {
        doInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Author> criteriaDelete = criteriaBuilder.createCriteriaDelete(Author.class);
            Root<Author> root = criteriaDelete.from(Author.class);

            criteriaDelete.where(criteriaBuilder.or(
                    criteriaBuilder.and(
                            criteriaBuilder.like(root.get("name"), "%author%"),
                            criteriaBuilder.like(root.get("lastName"), "%2%")
                    ),
                    criteriaBuilder.equal(root.get("name"), "Lermontov")
            ));
            session.createMutationQuery(criteriaDelete).executeUpdate();
        });
    }
}
