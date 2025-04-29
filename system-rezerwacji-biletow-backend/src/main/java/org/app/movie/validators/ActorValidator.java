package org.app.movie.validators;

import org.app.movie.repository.ActorRepository;

public class ActorValidator {
    private final ActorRepository actorRepository;

    public ActorValidator(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public void checkActorWithEmailExists(String email){
        actorRepository.findByEmail(email).ifPresent(foundActor -> {
            throw new RuntimeException("Email zajęty!");
        });
    }

}
