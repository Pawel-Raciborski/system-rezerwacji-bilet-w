package org.app.movie.web_service;

import org.app.movie.Spectacle;
import org.app.movie.dto.CreateSpectacleRequest;
import org.app.movie.dto.SpectacleDto;
import org.app.movie.dto.SpectacleResponse;
import org.app.movie.dto.SpectacleSeats;
import org.app.movie.mappers.SeatMapper;
import org.app.movie.mappers.SpectacleMapper;
import org.app.movie.services.SpectacleService;
import org.app.room.Seat;
import org.app.room.dto.SeatDto;
import org.app.room.service.SeatService;

import javax.jws.WebService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.app.movie.web_service.SpectacleWebService")
public class SpectacleWebServiceImpl implements SpectacleWebService {
    private final SpectacleService spectacleService;
    private final SeatService seatService;

    public SpectacleWebServiceImpl() {
        this.spectacleService = new SpectacleService();
        this.seatService = new SeatService();
    }

    @Override
    public SpectacleDto createSpectacle(CreateSpectacleRequest createSpectacleRequest) {
        return SpectacleMapper.mapToSpectacleDto(spectacleService.createSpectacle(createSpectacleRequest));
    }

    @Override
    public SpectacleResponse findAllForDate(String date) {
        List<SpectacleDto> collect = spectacleService.findAllForDate(date).stream().map(SpectacleMapper::mapToSpectacleDto).collect(Collectors.toList());
        return SpectacleResponse.builder().spectacles(collect).build();
    }

    @Override
    public SpectacleSeats getAvailablePlacesForSpectacle(UUID spectacleId) {
        Spectacle spectacle = spectacleService.findById(spectacleId);
        List<SeatDto> spectacleSeats = seatService.findSpectacleSeats(spectacle.getId()).stream().map(SeatMapper::mapToSeatDto).collect(Collectors.toList());

        return SpectacleSeats.builder()
                .seats(spectacleSeats)
                .build();
    }
}
