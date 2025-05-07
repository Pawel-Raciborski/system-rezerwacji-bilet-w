package org.app.room.repository;

import org.app.room.Seat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeatRepository {
    void save(Seat seat);

    List<Seat> findRoomSeats(UUID roomId);

    List<Seat> findAvailableSpectacleSeats(UUID spectacleId);

    Optional<Seat> findInReserved(UUID id, UUID seatId);

    void reserveSeat(UUID id, UUID seatId);
}
