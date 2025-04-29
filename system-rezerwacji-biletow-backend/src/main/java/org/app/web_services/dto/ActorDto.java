package org.app.web_services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
