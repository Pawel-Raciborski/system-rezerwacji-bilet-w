package org.app;

import org.app.movie.Actor;
import org.app.movie.repository.ActorRepositoryImpl;
import org.app.movie.services.ActorService;
import org.app.movie.validators.ActorValidator;
import org.app.web_services.dto.ActorDto;

public class Main {
    public static void main(String[] args) {
        ActorRepositoryImpl actorRepository = new ActorRepositoryImpl();
        ActorService actorService = new ActorService(actorRepository, new ActorValidator(actorRepository));

        Actor save = actorService.save(ActorDto.builder()
                .name("Jan")
                .surname("Kowalsi")
                .email("jan@mail.com")
                .phoneNumber("+11 211 231 321")
                .build());

        System.out.println(save.getActorId());
    }
}
