package org.app.room.repository;

import org.app.db.HibernateUtil;
import org.app.room.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<Room> findById(UUID roomId) {
        try(Session session = sessionFactory.openSession()){
            Room room = session.get(Room.class, roomId);
            return Optional.ofNullable(room);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
