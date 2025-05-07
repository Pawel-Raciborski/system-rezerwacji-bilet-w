package org.app.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "createReservationRequest")
public class CreateReservationRequest {
    @XmlElement
    private UUID spectacleId;

    @XmlElement
    private List<UUID> seatToReserveIds;

    @XmlElement
    private UUID userId;

    @XmlElement
    private String reservationDate;
}
