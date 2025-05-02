package org.app.web_services.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.app.movie.Actor;
import org.app.movie.repository.ActorRepositoryImpl;
import org.app.movie.services.ActorService;
import org.app.movie.validators.ActorValidator;
import org.app.web_services.ActorWebService;
import org.app.web_services.dto.ActorDto;
import org.app.web_services.dto.ActorList;
import org.app.web_services.mappers.ActorMapper;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.app.web_services.ActorWebService")
@AllArgsConstructor
public class ActorWebServiceImpl implements ActorWebService {
    private ActorService actorService;

    public ActorWebServiceImpl() {
        ActorRepositoryImpl actorRepository = new ActorRepositoryImpl();
        this.actorService = new ActorService(actorRepository, new ActorValidator(actorRepository));
    }

    @Override
    public ActorDto create(ActorDto actorDto) {
        Actor createdActor = actorService.save(actorDto);

        return ActorMapper.mapToDto(createdActor);
    }

    @Override
    public ActorList findAll() {
        List<ActorDto> actors = actorService.findAll().stream().map(ActorMapper::mapToDto).collect(Collectors.toCollection(ArrayList::new));
        return new ActorList(actors);
    }
}
