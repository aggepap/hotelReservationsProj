package org.hotelbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String reservationCode;
    private LocalDateTime reservationBookedDate;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Boolean advancePaid;
    private Boolean isActive;


    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Resident> residents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    public void addRoomToReservation(Room room){
        this.room = room;
    }
    public void addResidentToReservation(Resident resident){
        if(residents == null) {
            residents = new HashSet<>();
        }
        residents.add(resident);
        resident.setReservation(this);
    }

    @PrePersist
    protected void onCreate(){
        if (reservationCode == null){
            reservationCode = UUID.randomUUID().toString();
        }
        reservationBookedDate = LocalDateTime.now();
    }


}
