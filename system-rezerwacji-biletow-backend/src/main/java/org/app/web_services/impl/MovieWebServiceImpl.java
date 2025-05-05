package org.app.web_services.impl;

import org.app.movie.Movie;
import org.app.movie.dto.MovieDto;
import org.app.movie.dto.Movies;
import org.app.movie.mappers.MovieMapper;
import org.app.movie.services.MovieService;
import org.app.web_services.MovieWebService;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.app.web_services.MovieWebService")
public class MovieWebServiceImpl implements MovieWebService {
    private final MovieService movieService;
    public MovieWebServiceImpl() {
        movieService = new MovieService();
    }
    @Override
    public MovieDto save(MovieDto movieDto) {
        return MovieMapper.mapToMovieDto(movieService.save(movieDto));
    }

    @Override
    public Movies findByTitle(String title) {
        List<MovieDto> movies = movieService.findByTitle(title).stream().map(MovieMapper::mapToMovieDto).collect(Collectors.toCollection(ArrayList::new));
        return new Movies(movies);
    }

    @Override
    public void addActorToMovie(UUID movieId, ArrayList<UUID> actorIds) {
        System.out.println("Found movie with id: " + movieId);
        System.out.println("Actor ids: " + actorIds);
        actorIds.forEach(actorId -> movieService.addActor(movieId,actorId));
    }
}
