package org.app.web_services.impl;

import org.app.movie.dto.MovieDto;
import org.app.movie.mappers.MovieMapper;
import org.app.movie.services.MovieService;
import org.app.web_services.MovieWebService;

import javax.jws.WebService;

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
}
