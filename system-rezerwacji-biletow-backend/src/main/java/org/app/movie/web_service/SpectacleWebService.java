package org.app.movie.web_service;

import org.app.movie.Spectacle;
import org.app.movie.dto.CreateSpectacleRequest;
import org.app.movie.dto.SpectacleDto;
import org.app.movie.dto.SpectacleResponse;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface SpectacleWebService {

    @WebMethod
    @WebResult(name = "spectacle")
    SpectacleDto createSpectacle(CreateSpectacleRequest createSpectacleRequest);

    @WebMethod
    SpectacleResponse findAllForDate(String date);
}
