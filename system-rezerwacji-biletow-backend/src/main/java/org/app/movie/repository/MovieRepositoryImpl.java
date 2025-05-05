package org.app.movie.repository;

import org.app.db.HibernateUtil;
import org.app.movie.Actor;
import org.app.movie.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<Movie> findById(Long movieId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> findById = session.createQuery("FROM Movie m WHERE m.id = :id", Movie.class);
            findById.setParameter("id", movieId);
            return findById.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addActorToMovie(UUID movieId, UUID actorId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Movie movie = session.get(Movie.class, movieId);
            Actor actor = session.get(Actor.class, actorId);

            if (movie == null || actor == null) {
                throw new IllegalArgumentException("Nie znaleziono filmu lub aktora o podanym ID.");
            }

            movie.getActorMovies().add(actor);
            actor.getActorMovies().add(movie);

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas dodawania aktora do filmu.", e);
        }
    }

    @Override
    public List<Movie> findActorMovies(Long actorId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> query = session.createQuery("FROM Movie m JOIN FETCH m.actorMovies a where a.id = :actorId", Movie.class);
            query.setParameter("actorId", actorId);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
