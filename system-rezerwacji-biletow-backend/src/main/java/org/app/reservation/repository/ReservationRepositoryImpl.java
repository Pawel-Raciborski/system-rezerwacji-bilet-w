package org.app.reservation.repository;

import org.app.db.HibernateUtil;
import org.app.movie.Spectacle;
import org.app.reservation.Reservation;
import org.app.room.Seat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.*;

public class ReservationRepositoryImpl implements ReservationRepository {
    private final SessionFactory sessionFactory;

    public ReservationRepositoryImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Reservation save(Reservation reservation) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(reservation);
            session.getTransaction().commit();

            return reservation;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Reservation> findById(UUID reservationId) {
        try(Session session = sessionFactory.openSession()){
            Reservation reservation = session.get(Reservation.class, reservationId);

            return Optional.ofNullable(reservation);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeReservation(UUID reservationId) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Reservation reservation = session.get(Reservation.class, reservationId);

            if (reservation == null) {
                throw new RuntimeException("Nie znaleziono rezerwacji");
            }

            Spectacle spectacle = reservation.getSpectacle();
            Set<Seat> seatsToFree = reservation.getReservedSeats();

            spectacle.getReservedSeats().removeAll(seatsToFree);
            spectacle.getAvailableSeats().addAll(seatsToFree);

            session.update(spectacle);
            session.delete(reservation);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
