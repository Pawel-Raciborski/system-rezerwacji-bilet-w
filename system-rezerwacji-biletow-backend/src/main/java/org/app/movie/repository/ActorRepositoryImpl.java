package org.app.movie.repository;

import org.app.db.HibernateUtil;
import org.app.movie.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class ActorRepositoryImpl implements ActorRepository {
    private final SessionFactory sessionFactory;

    public ActorRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Actor save(Actor actor) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(actor);

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return actor;
    }

    @Override
    public Optional<Actor> findByEmail(String email) {
        Optional<Actor> optionalActor = Optional.empty();

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<Actor> query = session.createQuery("select a from Actor a where a.email = :email", Actor.class);
            query.setParameter("email", email);

            optionalActor = query.uniqueResultOptional();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return optionalActor;
    }

    @Override
    public List<Actor> findAll() {
        List<Actor> actors;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<Actor> findAll = session.createQuery("FROM Actor a", Actor.class);

            actors = findAll.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return actors;
    }
}
