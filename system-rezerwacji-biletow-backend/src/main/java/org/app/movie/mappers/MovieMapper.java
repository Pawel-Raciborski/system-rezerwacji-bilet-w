package org.app.movie.mappers;

import org.app.movie.Movie;
import org.app.movie.dto.MovieDto;

public interface MovieMapper {
    static final String PREFIX = "PT";
    static Movie mapToMovie(MovieDto movieDto){
        return Movie.builder()
                .director(DirectorMapper.mapToDirector(movieDto.getDirector()))
                .stars(movieDto.getStars())
                .length(PREFIX.concat(movieDto.getLength()))
                .posterData(movieDto.getPosterData())
                .description(movieDto.getDescription())
                .title(movieDto.getTitle())
                .build();
    }

    static MovieDto mapToMovieDto(Movie movie){
        return MovieDto.builder()
                .movieId(movie.getMovieId())
                .director(DirectorMapper.mapToDirectorDto(movie.getDirector()))
                .stars(movie.getStars())
                .length(movie.getLength())
                .posterData(movie.getPosterData())
                .description(movie.getDescription())
                .title(movie.getTitle())
                .build();
    }
}
