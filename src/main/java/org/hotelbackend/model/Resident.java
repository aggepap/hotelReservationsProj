package org.hotelbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "residents")
public class Resident extends PanacheEntityBase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String vat;
    @Column(unique = true)
    private String idNumber;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean isAdult;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonBackReference
    private Reservation reservation;



    @PrePersist
    private void checkAdult() {
        if (birthDate != null) {
            final int LEGAL_AGE = 18;
            Period period = Period.between(birthDate, LocalDate.now());

            if (period.getYears() >= LEGAL_AGE) {
                isAdult = true;
            } else {
                isAdult = false;
            }
        }
    }

}

