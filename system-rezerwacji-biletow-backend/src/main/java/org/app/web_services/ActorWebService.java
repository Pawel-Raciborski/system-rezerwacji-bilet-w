package org.app.web_services;

import org.app.movie.Actor;
import org.app.web_services.dto.ActorDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ActorWebService {

    @WebMethod
    Actor create(ActorDto actorDto);
}
