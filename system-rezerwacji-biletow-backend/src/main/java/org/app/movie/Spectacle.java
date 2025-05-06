package org.app.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.app.movie.Movie;
import org.app.room.Room;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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
}
