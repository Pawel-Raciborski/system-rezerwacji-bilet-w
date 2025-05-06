package org.app.room.repository;

import org.app.room.Seat;

import java.util.List;
import java.util.UUID;

public interface SeatRepository {
    void save(Seat seat);

    List<Seat> findRoomSeats(UUID roomId);

    List<Seat> findSpectacleSeats(UUID spectacleId);
}
