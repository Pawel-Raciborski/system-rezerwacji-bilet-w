package org.app.room.service;

import org.app.movie.Spectacle;
import org.app.reservation.Reservation;
import org.app.room.Room;
import org.app.room.Seat;
import org.app.room.repository.SeatRepository;
import org.app.room.repository.SeatRepositoryImpl;
import org.app.room.validator.SeatValidator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SeatService {
    private final SeatRepository seatRepository;
    private final SeatValidator seatValidator;

    public SeatService() {
        this.seatRepository = new SeatRepositoryImpl();
        this.seatValidator = new SeatValidator();
    }

    public void createSeatsForRoom(Room savedRoom) {
        Integer numberOfPlaces = savedRoom.getNumberOfPlaces();

        // creating square based seats
        int numberOfRows = (int) Math.sqrt(numberOfPlaces);
        System.out.println("NUMBER OF ROWS: " + numberOfRows);
        int row = 0;
        int seat = 0;
        int prevSeatNumber = 0;
        int startCharIndex = 65;
        for (; row < numberOfRows; row++) {
            startCharIndex += row;
            String rowNumber = "" + ((char) startCharIndex);
            System.out.println("ROW" + row);
            for (; seat < numberOfRows; seat++) {
                System.out.println("SEAT: " + seat);
                String seatNumber = String.valueOf(seat);
                Seat seatToCreate = Seat.builder()
                        .row(rowNumber.concat(seatNumber))
                        .placeNumber(seat)
                        .room(savedRoom)
                        .build();
                seatRepository.save(seatToCreate);
            }
            prevSeatNumber = seat;
            seat = 0;
        }

        int reminder = numberOfPlaces % numberOfRows;
        String rowNumber = "" + ((char) startCharIndex);
        if (reminder != 0) {
            for (; prevSeatNumber < numberOfRows + (reminder); prevSeatNumber++) {
                String seatNumber = String.valueOf(prevSeatNumber);
                Seat seatToCreate = Seat.builder()
                        .row(rowNumber.concat(seatNumber))
                        .placeNumber(seat)
                        .room(savedRoom)
                        .build();
                seatRepository.save(seatToCreate);
            }
        }
    }

    public List<Seat> findSpectacleSeats(UUID spectacleId) {
        return seatRepository.findAvailableSpectacleSeats(spectacleId);
    }

    public List<Seat> reserveSeats(Spectacle spectacle, List<UUID> seatToReserveIds) {
        seatValidator.checkAreAvailableSeats(spectacle, seatToReserveIds);
        List<Seat> reservedSeats = seatRepository.findAvailableSpectacleSeats(spectacle.getId()).stream()
                .filter(seat -> seatToReserveIds.contains(seat.getSeatId()))
                .collect(Collectors.toList());
        reservedSeats.forEach(seat -> seatRepository.reserveSeat(spectacle.getId(), seat.getSeatId()));
        return reservedSeats;
    }
}
