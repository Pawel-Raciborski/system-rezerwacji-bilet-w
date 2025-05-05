package org.app.room.repository;

import org.app.db.HibernateUtil;
import org.app.room.Seat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
