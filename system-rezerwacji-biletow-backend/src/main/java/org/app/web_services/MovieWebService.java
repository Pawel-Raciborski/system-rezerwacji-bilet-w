package org.app.web_services;

import org.app.movie.Movie;
import org.app.movie.dto.MovieDto;
import org.app.movie.dto.Movies;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MovieWebService {

    @WebMethod
    @WebResult(name="movie")
    MovieDto save(MovieDto movieDto);

    @WebMethod
    @WebResult(name="movies")
    Movies findByTitle(@WebParam(name = "title") String title);
}
