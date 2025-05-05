package org.app.movie.services;

import org.app.movie.Spectacle;
import org.app.movie.dto.CreateSpectacleRequest;
import org.app.movie.repository.MovieRepository;
import org.app.movie.repository.MovieRepositoryImpl;
import org.app.movie.repository.RoomRepository;

public class SpectacleService {
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    public SpectacleService() {
        this.movieRepository = new MovieRepositoryImpl();
        this.roomRepository = new RoomRepositoryImpl();
    }

    public Spectacle createSpectacle(CreateSpectacleRequest createSpectacleRequest){

    }
}
