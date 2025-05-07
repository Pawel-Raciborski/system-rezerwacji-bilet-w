package org.app.room;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "seat")
@ToString(of={"row","placeNumber"})
@EqualsAndHashCode(of={"seatId"})
public class Seat {

    @Id
    @Column(name = "seat_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID seatId;
    private String row;
    private Integer placeNumber;
    @ManyToOne
    private Room room;
}
