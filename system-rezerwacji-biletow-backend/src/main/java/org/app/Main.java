package org.app;

import org.app.services.ActorService;
import org.app.web_services.dto.ActorDto;

public class Main {
    public static void main(String[] args) {
        ActorService actorService = new ActorService();

        actorService.save(ActorDto.builder()
                        .name("Jan")
                        .surname("Kowalsi")
                        .email("jan@mail.com")
                        .phoneNumber("+11 211 231 321")
                .build());
    }
}
