package org.hotelbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;

import java.time.LocalDate;
import java.time.Period;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "residents")
public class Resident extends AbstractEntity{

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
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean isAdult;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonBackReference
    private Reservation reservation;

    @PrePersist
    private void checkAdult(){
        Period period  = Period.between(birthDate, LocalDate.now());
        if(period.getYears() >= 18){
            isAdult = true;
        }else{
            isAdult = false;
        }
    }

}
