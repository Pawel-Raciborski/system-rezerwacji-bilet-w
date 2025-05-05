package org.app.room.service;

import org.app.room.Room;
import org.app.room.dto.RoomDto;
import org.app.room.mapper.RoomMapper;
import org.app.room.repository.RoomRepository;
import org.app.room.repository.RoomRepositoryImpl;

public class RoomService {
    private final RoomRepository roomRepository;
    private final SeatService seatService;

    public RoomService() {
        this.roomRepository = new RoomRepositoryImpl();
        this.seatService = new SeatService();
    }

    public Room create(RoomDto roomDto){
        Room room = RoomMapper.mapToRoom(roomDto);
        Room savedRoom = roomRepository.create(room);
        createSeatsIfNumberOfPlacesIsGraterThanZero(savedRoom);
        return savedRoom;
    }

    private void createSeatsIfNumberOfPlacesIsGraterThanZero(Room savedRoom) {
        if(savedRoom.getNumberOfPlaces() > 0){
            seatService.createSeatsForRoom(savedRoom);
        }
    }
}
