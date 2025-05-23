package org.app.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "createSpectacleRequest")
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpectacleRequest {
    @XmlElement
    private String date;

    @XmlElement
    private UUID movieId;

    @XmlElement
    private UUID roomId;
}
