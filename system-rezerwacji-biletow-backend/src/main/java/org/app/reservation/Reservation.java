package org.app.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.app.movie.Spectacle;
import org.app.room.Seat;
import org.app.user.model.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
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
