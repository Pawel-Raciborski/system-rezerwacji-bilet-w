package org.app.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.movie.Movie;
import org.app.room.Room;
import org.app.room.dto.RoomDto;

import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "spectacleDto")
public class SpectacleDto {

    @XmlElement
    private UUID id;

    @XmlElement
    private RoomDto room;

    @XmlElement
    private MovieDto movie;

    @XmlElement
    private String date;
}
