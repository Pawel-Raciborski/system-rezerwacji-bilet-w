package org.app.room.repository;

import org.app.db.HibernateUtil;
import org.app.room.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RoomRepositoryImpl implements RoomRepository {
    private final SessionFactory sessionFactory;

    public RoomRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Room create(Room room) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            session.save(room);
            session.getTransaction().commit();
            return room;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
