package org.app.movie.mappers;

import org.app.room.Seat;
import org.app.room.dto.SeatDto;

public interface SeatMapper {
    static SeatDto mapToSeatDto(Seat seat){
        return SeatDto.builder()
                .seatId(seat.getSeatId())
                .placeNumber(seat.getPlaceNumber())
                .row(seat.getRow())
                .build();
    }
}
