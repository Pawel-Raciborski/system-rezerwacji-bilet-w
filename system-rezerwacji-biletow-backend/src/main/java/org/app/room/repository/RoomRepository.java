package org.app.room.repository;

import org.app.room.Room;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository {
    Room create(Room room);

    Optional<Room> findById(UUID roomId);
}
