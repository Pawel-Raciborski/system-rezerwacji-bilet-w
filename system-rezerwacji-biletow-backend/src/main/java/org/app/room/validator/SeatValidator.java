package org.app.room.validator;

import org.app.movie.Spectacle;
import org.app.room.Seat;
import org.app.room.repository.SeatRepository;
import org.app.room.repository.SeatRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SeatValidator {
    private final SeatRepository seatRepository;

    public SeatValidator() {
        this.seatRepository = new SeatRepositoryImpl();
    }

    public void checkAreAvailableSeats(Spectacle spectacle, List<UUID> seatToReserveIds) {
        seatToReserveIds.forEach(seatId -> {
            Optional<Seat> inReserved = seatRepository.findInReserved(spectacle.getId(), seatId);

            if(inReserved.isPresent()){
                Seat seat = inReserved.get();
                throw new RuntimeException(String.format("Miejsce (%s,%s) jest już zarezerwowane!",seat.getRow(),seat.getPlaceNumber()));
            }
        });
    }
}
