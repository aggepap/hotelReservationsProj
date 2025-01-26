package org.hotelbackend.model;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String roomNumber;
    private String floor;
    private Integer capacity;
    private Boolean hasSeaView;
    private Boolean isAvailable;

    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();
}
