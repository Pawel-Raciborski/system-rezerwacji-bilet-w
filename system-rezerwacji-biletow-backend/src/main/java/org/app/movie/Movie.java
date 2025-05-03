package org.app.movie;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="move_id")
    private Long movieId;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name="length")
    private String length;

    @Lob
    @Column(name="poseter_data")
    private byte[] posterData;

    @Column(name="stars")
    private BigDecimal stars;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Director director;

    @ManyToMany(mappedBy = "actorMovies", fetch = FetchType.LAZY)
    private Set<Actor> actorMovies;

    @OneToMany
    private Set<Spectacle> spectacles;
}
