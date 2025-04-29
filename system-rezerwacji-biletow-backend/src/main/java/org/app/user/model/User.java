package org.app.user.model;

import org.app.reservation.Reservation;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="user_table")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String password;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Reservation> reservations;
}
