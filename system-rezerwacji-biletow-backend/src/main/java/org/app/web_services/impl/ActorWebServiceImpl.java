package org.app.web_services.impl;

import lombok.RequiredArgsConstructor;
import org.app.model.Actor;
import org.app.services.ActorService;
import org.app.web_services.ActorWebService;
import org.app.web_services.dto.ActorDto;

import javax.jws.WebService;

@WebService(endpointInterface = "org.app.web_services.ActorWebService")
@RequiredArgsConstructor
public class ActorWebServiceImpl implements ActorWebService {
    private final ActorService actorService;

    @Override
    public Actor create(ActorDto actorDto) {
        return actorService.save(actorDto);
    }
}
