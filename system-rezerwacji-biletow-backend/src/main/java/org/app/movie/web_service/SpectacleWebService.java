package org.app.movie.web_service;

import org.app.movie.Spectacle;
import org.app.movie.dto.CreateSpectacleRequest;
import org.app.movie.dto.SpectacleDto;
import org.app.movie.dto.SpectacleResponse;
import org.app.movie.dto.SpectacleSeats;
import org.app.room.dto.RoomSeats;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.UUID;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface SpectacleWebService {

    @WebMethod
    @WebResult(name = "spectacle")
    SpectacleDto createSpectacle(CreateSpectacleRequest createSpectacleRequest);

    @WebMethod
    SpectacleResponse findAllForDate(String date);

    @WebMethod
    @WebResult(name="availablePlace")
    SpectacleSeats getAvailablePlacesForSpectacle(UUID spectacleId);
}
