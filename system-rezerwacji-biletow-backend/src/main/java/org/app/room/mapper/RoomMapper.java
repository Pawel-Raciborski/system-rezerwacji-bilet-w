package org.app.room.mapper;

import org.app.room.Room;
import org.app.room.dto.RoomDto;

public interface RoomMapper {
    static Room mapToRoom(RoomDto roomDto) {
        return Room.builder()
                .roomId(roomDto.getRoomId())
                .roomNumber(roomDto.getRoomNumber())
                .numberOfPlaces(roomDto.getNumberOfPlaces())
                .build();
    }

    static RoomDto mapToRoomDto(Room room) {
        return RoomDto.builder()
                .roomId(room.getRoomId())
                .roomNumber(room.getRoomNumber())
                .numberOfPlaces(room.getNumberOfPlaces())
                .build();
    }
}
