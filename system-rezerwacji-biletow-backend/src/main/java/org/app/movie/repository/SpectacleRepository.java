package org.app.movie.repository;

import org.app.movie.Spectacle;
import org.app.room.Room;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpectacleRepository {
    Spectacle save(Spectacle spectacle);

    List<Spectacle> findAllForDate(String date);

    List<Spectacle> findWithRoomIdAndDate(UUID roomId, LocalDateTime date);

    Optional<Spectacle> findSpectaclesBetweenDates(UUID roomId, LocalDateTime left, LocalDateTime right);
}
