package org.app.movie;

import org.app.movie.Movie;
import org.app.room.Room;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="spectacle")
public class Spectacle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Movie movie;
    private LocalDateTime date;
}
