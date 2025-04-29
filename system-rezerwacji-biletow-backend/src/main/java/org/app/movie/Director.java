package org.app.movie;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "director")
    private Set<Movie> movies;
}
