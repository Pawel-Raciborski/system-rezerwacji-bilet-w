package org.app.movie.repository;

import org.app.movie.Movie;

import java.util.List;

public interface MovieRepository {
    Movie save(Movie movie);

    List<Movie> findByTitle(String title);
}
