package org.app.room.web_service;

import org.app.response.Response;
import org.app.room.Room;
import org.app.room.dto.RoomDetails;
import org.app.room.dto.RoomDto;
import org.app.room.mapper.RoomMapper;
import org.app.room.service.RoomService;

import javax.jws.WebService;
import java.util.UUID;

@WebService(endpointInterface = "org.app.room.web_service.RoomWebService")
public class RoomWebServiceImpl implements RoomWebService {
    private final RoomService roomService;

    public RoomWebServiceImpl() {
        this.roomService = new RoomService();
    }

    @Override
    public Response<RoomDto> create(RoomDto roomDto) {
        RoomDto room = RoomMapper.mapToRoomDto(roomService.create(roomDto));
        return Response.<RoomDto>builder()
                .responseMessage("Pomyślnie utworzono salę")
                .data(room)
                .build();
    }

    @Override
    public RoomDetails getRoomDetails(UUID roomId) {
        return null;
    }
}
