package org.app.room.repository;

import org.app.db.HibernateUtil;
import org.app.room.Seat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class SeatRepositoryImpl implements SeatRepository {
    private final SessionFactory sessionFactory;

    public SeatRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Seat seat) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(seat);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Seat> findRoomSeats(UUID roomId) {
        try(Session session = sessionFactory.openSession()){
            Query<Seat> query = session.createQuery("SELECT s FROM Seat s JOIN s.room r WHERE r.id = :roomId", Seat.class);
            query.setParameter("roomId",roomId);

            return query.getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Seat> findSpectacleSeats(UUID spectacleId) {
        try(Session session = sessionFactory.openSession()){
            Query<Seat> query = session.createQuery("SELECT avS FROM Spectacle s JOIN s.availableSeats avS WHERE s.id = :spectacleId ORDER BY avS.placeNumber, avS.row", Seat.class);
            query.setParameter("spectacleId",spectacleId);

            return query.getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
