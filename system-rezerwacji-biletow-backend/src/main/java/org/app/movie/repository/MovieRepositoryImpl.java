package org.app.movie.repository;

import org.app.db.HibernateUtil;
import org.app.movie.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MovieRepositoryImpl implements MovieRepository {
    private final SessionFactory sessionFactory;

    public MovieRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Movie save(Movie movie) {
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            session.save(movie.getDirector());
            session.save(movie);
            session.getTransaction().commit();

            return movie;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
