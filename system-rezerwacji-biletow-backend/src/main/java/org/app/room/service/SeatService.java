package org.app.room.service;

import org.app.room.Room;
import org.app.room.Seat;
import org.app.room.repository.SeatRepository;
import org.app.room.repository.SeatRepositoryImpl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService() {
        this.seatRepository = new SeatRepositoryImpl();
    }

    public void createSeatsForRoom(Room savedRoom) {
        Integer numberOfPlaces = savedRoom.getNumberOfPlaces();

        // creating square based seats
        Set<Seat> seats = new HashSet<>();
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
                System.out.println("Seat: " + seatToCreate);
                seats.add(seatToCreate);
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
                seats.add(seatToCreate);
            }
        }

        seats.forEach(seatRepository::save);
    }
}
