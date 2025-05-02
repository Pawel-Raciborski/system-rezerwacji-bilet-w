package org.app.web_services.mappers;

import org.app.movie.Actor;
import org.app.web_services.dto.ActorDto;

public interface ActorMapper {
    static ActorDto mapToDto(Actor actor){
        return ActorDto.builder()
                .id(actor.getActorId())
                .name(actor.getName())
                .surname(actor.getSurname())
                .email(actor.getEmail())
                .phoneNumber(actor.getPhoneNumber())
                .build();
    }

    static Actor mapFromDto(ActorDto actor){
        return Actor.builder()
                .actorId(actor.getId())
                .name(actor.getName())
                .surname(actor.getSurname())
                .email(actor.getEmail())
                .phoneNumber(actor.getPhoneNumber())
                .build();
    }
}
