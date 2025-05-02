package org.app.web_services;

import org.app.movie.Actor;
import org.app.web_services.dto.ActorDto;
import org.app.web_services.dto.ActorList;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ActorWebService {

    @WebMethod
    ActorDto create(ActorDto actorDto);

    @WebMethod
    @WebResult(name = "actorsResponse")
    ActorList findAll();
}
