package org.app.movie.services;

import lombok.NoArgsConstructor;
import org.app.movie.Actor;
import org.app.movie.Movie;
import org.app.movie.dto.MovieDto;
import org.app.movie.mappers.MovieMapper;
import org.app.movie.repository.MovieRepository;
import org.app.movie.repository.MovieRepositoryImpl;


public class MovieService {
    private final MovieRepository movieRepository;
    public MovieService() {
        movieRepository = new MovieRepositoryImpl();
    }

    public Movie save(MovieDto movie){
        return movieRepository.save(MovieMapper.mapToMovie(movie));
    }

    public void addActor(Movie movie, Actor actor){
        movie.getActorMovies().add(actor);

        movieRepository.save(movie);
    }


}
