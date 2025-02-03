package org.hotelbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Reservation extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String reservationCode;
    private LocalDateTime reservationBookedDate;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Integer guestsNumber;
    private Boolean advancePaid;
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Resident> residents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Transactional
    public void addRoomToReservation(Room room){
        this.room = room;
        room.getReservations().add(this);
    }
    @Transactional
    public void addResidentToReservation(Resident resident){
        if(residents == null) {
            residents = new HashSet<>();
        }
        residents.add(resident);
        resident.setReservation(this);
    }
    @Transactional
    public void removeResidentFromReservation(Resident resident){
        residents.remove(resident);
        resident.setReservation(null);
    }

    @PrePersist
    protected void onCreate(){
        if (reservationCode == null){
            reservationCode = UUID.randomUUID().toString();
        }
        reservationBookedDate = LocalDateTime.now();
    }


}
