package org.app.movie.repository;

import org.app.db.HibernateUtil;
import org.app.movie.Spectacle;
import org.app.room.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpectacleRepositoryImpl implements SpectacleRepository {
    private final SessionFactory sessionFactory;

    public SpectacleRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Spectacle save(Spectacle spectacle) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(spectacle);

            session.getTransaction().commit();
            return spectacle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Spectacle> findAllForDate(String date) {
        try (Session session = sessionFactory.openSession()) {
            Query<Spectacle> query = session.createQuery("FROM Spectacle s JOIN FETCH s.room JOIN FETCH s.movie WHERE DATE(s.date) = :date ", Spectacle.class);
            LocalDate localDate = LocalDate.parse(date);
            query.setParameter("date", localDate);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Spectacle> findWithRoomIdAndDate(UUID roomId, LocalDateTime date) {
        try (Session session = sessionFactory.openSession()) {
            Query<Spectacle> query = session.createQuery("FROM Spectacle s JOIN FETCH s.room r WHERE r.id = :roomId and s.date = :date",Spectacle.class);
            query.setParameter("roomId", roomId);
            query.setParameter("date", date);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Spectacle> findSpectaclesBetweenDates(UUID roomId, LocalDateTime left, LocalDateTime right) {
        try(Session session = sessionFactory.openSession()){
            Query<Spectacle> query = session.createQuery("SELECT s FROM Spectacle s JOIN s.room r WHERE r.id = :roomId AND s.date BETWEEN :left and :right",Spectacle.class);
            query.setParameter("roomId", roomId);
            query.setParameter("left", left);
            query.setParameter("right",right);

            return query.uniqueResultOptional();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
