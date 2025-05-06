package org.app.movie.services;

import org.app.config.DateTimeFormatUtil;
import org.app.movie.Movie;
import org.app.movie.Spectacle;
import org.app.movie.dto.CreateSpectacleRequest;
import org.app.movie.repository.MovieRepository;
import org.app.movie.repository.MovieRepositoryImpl;
import org.app.movie.repository.SpectacleRepository;
import org.app.movie.repository.SpectacleRepositoryImpl;
import org.app.movie.validators.SpectacleValidator;
import org.app.room.Room;
import org.app.room.repository.RoomRepository;
import org.app.room.repository.RoomRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

public class SpectacleService {
    private final SpectacleValidator spectacleValidator;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final SpectacleRepository spectacleRepository;
    public SpectacleService() {
        this.movieRepository = new MovieRepositoryImpl();
        this.roomRepository = new RoomRepositoryImpl();
        this.spectacleRepository = new SpectacleRepositoryImpl();
        this.spectacleValidator = new SpectacleValidator();
    }

    public Spectacle createSpectacle(CreateSpectacleRequest createSpectacleRequest){
        LocalDateTime date = DateTimeFormatUtil.parseDate(createSpectacleRequest.getDate().trim());
        spectacleValidator.checkIsGivenRoomFreeForGivenHour(createSpectacleRequest.getRoomId(),date);

        Movie movie = movieRepository.findById(createSpectacleRequest.getMovieId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono filmu z podanym id!"));
        Room room = roomRepository.findById(createSpectacleRequest.getRoomId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono sali z podanym id!"));

        spectacleValidator.validateIsReservationForRoomBeforeAndAfterCurrentMovie(movie, room,date);

        Spectacle spectacle = buildSpectacle(movie, room, date);

        return spectacleRepository.save(spectacle);
    }

    private Spectacle buildSpectacle(Movie movie, Room room, LocalDateTime date) {
        return Spectacle.builder()
                .movie(movie)
                .room(room)
                .date(date)
                .build();
    }

    public List<Spectacle> findAllForDate(String date) {
        return spectacleRepository.findAllForDate(date);
    }
}
