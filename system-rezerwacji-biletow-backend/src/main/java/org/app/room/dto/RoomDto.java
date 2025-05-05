package org.app.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="room")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomDto {
    @XmlElement
    private UUID roomId;
    @XmlElement
    private String roomNumber;
    @XmlElement
    private Integer numberOfPlaces;
}
