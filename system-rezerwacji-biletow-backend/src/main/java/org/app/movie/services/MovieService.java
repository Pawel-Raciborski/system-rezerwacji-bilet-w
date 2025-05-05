package org.app.movie.services;

import org.app.movie.Actor;
import org.app.movie.Movie;
import org.app.movie.dto.MovieDto;
import org.app.movie.mappers.MovieMapper;
import org.app.movie.repository.ActorRepository;
import org.app.movie.repository.ActorRepositoryImpl;
import org.app.movie.repository.MovieRepository;
import org.app.movie.repository.MovieRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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

    public void addActor(UUID movie, UUID actorId){
        movieRepository.addActorToMovie(movie,actorId);
    }

    public List<Movie> findByTitle(String title){
        return movieRepository.findByTitle(title);
    }


    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Nie znaleziono filmu!"));
    }
}
