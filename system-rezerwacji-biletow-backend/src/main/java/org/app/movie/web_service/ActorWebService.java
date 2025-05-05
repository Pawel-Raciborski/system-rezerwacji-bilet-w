package org.app.movie.web_service;

import org.app.movie.dto.ActorDto;
import org.app.movie.dto.ActorList;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ActorWebService {

    @WebMethod
    ActorDto create(ActorDto actorDto);

    @WebMethod
    @WebResult(name = "actorsResponse")
    ActorList findAll();
}
