package org.app.movie.web_service;

import org.app.movie.dto.CreateSpectacleRequest;
import org.app.movie.dto.SpectacleDto;
import org.app.movie.dto.SpectacleResponse;
import org.app.movie.mappers.SpectacleMapper;
import org.app.movie.services.SpectacleService;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.app.movie.web_service.SpectacleWebService")
public class SpectacleWebServiceImpl implements SpectacleWebService {
    private final SpectacleService spectacleService;

    public SpectacleWebServiceImpl() {
        this.spectacleService = new SpectacleService();
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
}
