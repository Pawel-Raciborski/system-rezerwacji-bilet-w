package org.app.user.repository;

import org.app.db.HibernateUtil;
import org.app.user.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<User> query = session.createQuery("from User u where u.email = :email and u.password = :password", User.class);

            query.setParameter("email",email);
            query.setParameter("password",password);

            Optional<User> user = query.uniqueResultOptional();
            session.getTransaction().commit();
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public User save(User user) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(user);

            session.getTransaction().commit();
            return user;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(UUID userId) {
        try(Session session = sessionFactory.openSession()){
            User user = session.get(User.class, userId);

            return Optional.ofNullable(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
