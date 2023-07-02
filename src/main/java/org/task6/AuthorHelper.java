package org.task6;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.task6.entity.Author;

import java.util.List;

public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

       // Criteria cb = session.createCriteria(Author.class);// не использовать session.createCriteria, т.к. deprecated

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root);
        Query<Author> query = session.createQuery(criteriaQuery);

        List<Author> authorList = query.list();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = (Author) session.get(Author.class, id); // получение объекта по id
        return author;
    }

    public Author addAuthor(Author author){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(author); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();

        return author;

    }
}
