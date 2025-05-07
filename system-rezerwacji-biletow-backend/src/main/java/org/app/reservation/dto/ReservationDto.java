package org.app.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.movie.Spectacle;
import org.app.movie.dto.SpectacleDto;
import org.app.room.Seat;
import org.app.room.dto.SeatDto;
import org.app.user.model.LoggedUser;
import org.app.user.model.User;
import org.app.user.model.UserDto;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "reservation")
public class ReservationDto {
    @XmlElement
    private UUID id;
    @XmlElement
    private SpectacleDto spectacle;
    @XmlElement
    private List<SeatDto> reservedSeats;
    @XmlElement
    private LoggedUser user;
    @XmlElement
    private String reservationDate;
}
