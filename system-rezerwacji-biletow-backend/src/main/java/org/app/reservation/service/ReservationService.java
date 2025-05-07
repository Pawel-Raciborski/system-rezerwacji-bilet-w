package org.app.reservation.service;

import org.app.config.DateTimeFormatUtil;
import org.app.movie.Spectacle;
import org.app.movie.services.SpectacleService;
import org.app.reservation.Reservation;
import org.app.reservation.dto.CreateReservationRequest;
import org.app.reservation.repository.ReservationRepository;
import org.app.reservation.repository.ReservationRepositoryImpl;
import org.app.room.Seat;
import org.app.room.service.SeatService;
import org.app.user.model.User;
import org.app.user.services.UserService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ReservationService {
    private final SeatService seatService;
    private final UserService userService;
    private final SpectacleService spectacleService;
    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.seatService = new SeatService();
        this.userService = new UserService();
        this.spectacleService = new SpectacleService();
        this.reservationRepository = new ReservationRepositoryImpl();
    }

    public Reservation create(CreateReservationRequest reservationRequest) {
        Spectacle spectacle = spectacleService.findById(reservationRequest.getSpectacleId());
        User user = userService.findById(reservationRequest.getUserId());
        Set<Seat> reservedSeats = new HashSet<>(seatService.reserveSeats(spectacle, reservationRequest.getSeatToReserveIds()));
        LocalDateTime reservationDateTime = DateTimeFormatUtil.parseDate(reservationRequest.getReservationDate());

        Reservation reservation = buildReservation(spectacle, user, reservedSeats, reservationDateTime);

        return reservationRepository.save(reservation);
    }

    private Reservation buildReservation(Spectacle spectacle, User user, Set<Seat> reservedSeats, LocalDateTime reservationDateTime) {
        return Reservation.builder()
                .spectacle(spectacle)
                .reservedSeats(reservedSeats)
                .reservationDate(reservationDateTime)
                .user(user)
                .build();
    }

    public void removeReservation(UUID reservationId) {
        reservationRepository.removeReservation(reservationId);
    }
}
