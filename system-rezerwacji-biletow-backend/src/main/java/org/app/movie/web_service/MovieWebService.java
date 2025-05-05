package org.app.movie.web_service;

import org.app.movie.Movie;
import org.app.movie.dto.MovieDto;
import org.app.movie.dto.Movies;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface MovieWebService {

    @WebMethod
    @WebResult(name="movie")
    MovieDto save(MovieDto movieDto);

    @WebMethod
    @WebResult(name="movies")
    Movies findByTitle(@WebParam(name = "title") String title);

    @WebMethod(operationName = "addActorToMovie")
    void addActorToMovie(@WebParam(name = "movieId") UUID movieId, @WebParam(name = "actorIds") ArrayList<UUID> actorIds);
}
