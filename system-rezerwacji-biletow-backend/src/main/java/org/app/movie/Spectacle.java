package org.app.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.app.movie.Movie;
import org.app.room.Room;
import org.app.room.Seat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="spectacle")
public class Spectacle {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Movie movie;
    private LocalDateTime date;
    private Integer availablePlaces;

    @ManyToMany
    @JoinTable(
            name = "spectacle_reserved_seats",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name="seat_id")}
    )
    private Set<Seat> reservedSeats;

    @ManyToMany
    @JoinTable(
            name = "spectacle_available_seats",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name="seat_id")}
    )
    private Set<Seat> availableSeats;
}
