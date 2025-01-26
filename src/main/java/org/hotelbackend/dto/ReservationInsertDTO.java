package org.hotelbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationInsertDTO {

    private String firstname;
    private String lastname;
    private String vat;
    private String idNumber;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private CountryCode countryCode;
    private Gender gender;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private String roomNumber;
    private Integer guestsNumber;
    private Boolean advancePaid;

    public ReservationInsertDTO(LocalDate reservationStartDate, LocalDate reservationEndDate, Integer guestsNumber) {
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.guestsNumber = guestsNumber;
    }
}
