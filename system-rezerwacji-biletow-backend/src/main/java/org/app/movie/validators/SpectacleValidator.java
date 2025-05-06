package org.app.movie.validators;

import org.app.movie.Movie;
import org.app.movie.Spectacle;
import org.app.movie.repository.MovieRepository;
import org.app.movie.repository.MovieRepositoryImpl;
import org.app.movie.repository.SpectacleRepository;
import org.app.movie.repository.SpectacleRepositoryImpl;
import org.app.room.Room;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpectacleValidator {
    private final SpectacleRepository spectacleRepository;
    private final MovieRepository movieRepository;

    public SpectacleValidator() {
        this.spectacleRepository = new SpectacleRepositoryImpl();
        this.movieRepository = new MovieRepositoryImpl();
    }

    public void checkIsGivenRoomFreeForGivenHour(UUID roomId, LocalDateTime date) {
        List<Spectacle> withRoomIdAndDate = spectacleRepository.findWithRoomIdAndDate(roomId, date);
        if(!withRoomIdAndDate.isEmpty()){
            throw new RuntimeException("Podana godzina jest już zajęta!");
        }
    }

    public void validateIsReservationForRoomBeforeAndAfterCurrentMovie(Movie movie, Room room, LocalDateTime date) {
        String length = movie.getLength();
        Duration duration = Duration.parse(length);
        LocalDateTime left = date.minus(duration).minusMinutes(15);
        LocalDateTime right = date.plus(duration).plusMinutes(15);

        Optional<Spectacle> spectacle = spectacleRepository.findSpectaclesBetweenDates(room.getRoomId(),left,right);
        if(spectacle.isPresent()){
            throw new RuntimeException("Podana godzina koliduje z innym seansem w tej sali!");
        }
    }
}
