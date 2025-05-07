package org.app.reservation.repository;

import org.app.reservation.Reservation;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {
    Reservation save(Reservation reservation);

    Optional<Reservation> findById(UUID reservationId);

    void removeReservation(UUID reservation);
}
