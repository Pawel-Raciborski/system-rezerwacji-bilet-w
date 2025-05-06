package org.app.movie.repository;

import org.app.movie.Actor;
import org.app.movie.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository {
    Movie save(Movie movie);

    List<Movie> findByTitle(String title);

    Optional<Movie> findById(UUID movieId);

    void addActorToMovie(UUID movieId, UUID actorId);

    List<Movie> findActorMovies(UUID actorId);
}
