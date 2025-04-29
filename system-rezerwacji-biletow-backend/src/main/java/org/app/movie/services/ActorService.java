package org.app.movie.services;

import lombok.RequiredArgsConstructor;
import org.app.db.HibernateUtil;
import org.app.movie.Actor;
import org.app.movie.repository.ActorRepository;
import org.app.movie.validators.ActorValidator;
import org.app.web_services.dto.ActorDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorValidator actorValidator;

    public Actor save(ActorDto actorDto){
        Actor newActor = buildNewActor(actorDto);

        actorValidator.checkActorWithEmailExists(newActor.getEmail());

        return actorRepository.save(newActor);
    }

    private static Actor buildNewActor(ActorDto actorDto) {
        return Actor.builder()
                .name(actorDto.getName())
                .surname(actorDto.getSurname())
                .email(actorDto.getEmail())
                .phoneNumber(actorDto.getPhoneNumber())
                .build();
    }
}
