package org.app.reservation;

import org.app.movie.Spectacle;
import org.app.room.Seat;
import org.app.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Spectacle spectacle;
    @ManyToMany
    @JoinTable(
            name = "reservation_seat",
            joinColumns = {@JoinColumn(name="id")},
            inverseJoinColumns = {@JoinColumn(name="seat_id")}
    )
    private Set<Seat> reservedSeats;
    @ManyToOne
    private User user;

    private LocalDateTime reservationDate;
}
