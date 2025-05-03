package org.app.movie.services;

import lombok.NoArgsConstructor;
import org.app.movie.Actor;
import org.app.movie.Movie;
import org.app.movie.dto.MovieDto;
import org.app.movie.mappers.MovieMapper;
import org.app.movie.repository.ActorRepository;
import org.app.movie.repository.ActorRepositoryImpl;
import org.app.movie.repository.MovieRepository;
import org.app.movie.repository.MovieRepositoryImpl;

import java.util.List;


public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    public MovieService() {
        movieRepository = new MovieRepositoryImpl();
        actorRepository = new ActorRepositoryImpl();
    }

    public Movie save(MovieDto movie){
        return movieRepository.save(MovieMapper.mapToMovie(movie));
    }

    public void addActor(Movie movie, Actor actor){
        movie.getActorMovies().add(actor);
        actorRepository.save(actor);
        movieRepository.save(movie);
    }

    public List<Movie> findByTitle(String title){
        return movieRepository.findByTitle(title);
    }


}
