package org.app.room;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private String row;
    private Integer placeNumber;
    @ManyToOne
    private Room room;
}
