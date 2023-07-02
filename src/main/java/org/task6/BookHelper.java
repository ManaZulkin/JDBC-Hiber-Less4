package org.task6;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.task6.entity.Book;

import java.util.List;

public class BookHelper {
    private SessionFactory factory;
    public BookHelper(){
        factory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList(){
        try(Session session = factory.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root);
            Query<Book> query = session.createQuery(criteriaQuery);

            return query.list();
        }
    }

    public Book getBookById(long id){
        try(Session session = factory.openSession()){
            Book book = session.get(Book.class, id);
            return book;
        }
    }

    public Book addBook(Book book){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
            return book;
        }

    }
}
