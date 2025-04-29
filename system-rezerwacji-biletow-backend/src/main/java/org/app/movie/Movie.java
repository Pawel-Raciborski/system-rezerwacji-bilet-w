package org.app.movie;



import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="move_id")
    private Long movieId;

    @Column(name="length")
    private Duration length;

    @Column(name="poster_path")
    private String posterPath;

    @Column(name="stars")
    private BigDecimal stars;

    @ManyToOne(fetch = FetchType.LAZY)
    private Director director;
    @ManyToMany(mappedBy = "actorMovies", fetch = FetchType.LAZY)
    private Set<Actor> actorMovies;

    @OneToMany
    private Set<Spectacle> spectacles;
}
