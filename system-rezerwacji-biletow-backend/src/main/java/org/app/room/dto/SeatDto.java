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
@XmlRootElement(name="seat")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeatDto {
    @XmlElement
    private UUID seatId;
    @XmlElement
    private String row;
    @XmlElement
    private Integer placeNumber;
}
