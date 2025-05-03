package org.app.movie.repository;

import org.app.db.HibernateUtil;
import org.app.movie.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MovieRepositoryImpl implements MovieRepository {
    private final SessionFactory sessionFactory;

    public MovieRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Movie save(Movie movie) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(movie.getDirector());
            session.save(movie);
            session.getTransaction().commit();

            return movie;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movie> findByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query<Movie> query = session.createQuery("FROM Movie m WHERE m.title like :title", Movie.class);

            query.setParameter("title", title.concat("%"));
            List<Movie> moviesWithTitle = query.getResultList();
            session.getTransaction().commit();
            return moviesWithTitle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
