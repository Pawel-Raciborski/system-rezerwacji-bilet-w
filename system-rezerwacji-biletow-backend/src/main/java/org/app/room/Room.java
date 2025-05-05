package org.app.room;

import org.app.movie.Spectacle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name="room_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID roomId;
    private String roomNumber;
    private Integer numberOfPlaces;
    @OneToMany
    private Set<Seat> seats;

    @OneToMany
    private Set<Spectacle> spectacles;
}
