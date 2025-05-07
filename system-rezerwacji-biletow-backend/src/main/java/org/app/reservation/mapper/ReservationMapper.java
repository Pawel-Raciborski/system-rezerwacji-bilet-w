package org.app.reservation.mapper;

import org.app.config.DateTimeFormatUtil;
import org.app.movie.mappers.SeatMapper;
import org.app.movie.mappers.SpectacleMapper;
import org.app.reservation.Reservation;
import org.app.reservation.dto.ReservationDto;
import org.app.user.mapper.UserMapper;

import java.util.stream.Collectors;

public interface ReservationMapper {
    static ReservationDto mapToDto(Reservation reservation){
        return ReservationDto.builder()
                .id(reservation.getId())
                .reservedSeats(reservation.getReservedSeats().stream().map(SeatMapper::mapToSeatDto).collect(Collectors.toList()))
                .spectacle(SpectacleMapper.mapToSpectacleDto(reservation.getSpectacle()))
                .user(UserMapper.mapToLoggedUser(reservation.getUser()))
                .reservationDate(DateTimeFormatUtil.parseToString(reservation.getReservationDate()))
                .build();
    }
}
