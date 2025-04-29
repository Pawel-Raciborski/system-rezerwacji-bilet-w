package org.app.room;

import org.app.movie.Spectacle;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomNumber;
    private Integer numberOfPlaces;
    @OneToMany
    private Set<Seat> seats;

    @OneToMany
    private Set<Spectacle> spectacles;
}
