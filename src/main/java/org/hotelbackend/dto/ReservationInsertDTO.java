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

    @NotNull(message = "Firstname is required")
    private String firstname;
    @NotNull(message = "Lastname is required")
    private String lastname;
    private String vat;
    @NotNull(message = "ID Number is required")
    private String idNumber;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private CountryCode countryCode;
    private Gender gender;
    @NotNull(message = "Start Date is required")
    private LocalDate reservationStartDate;
    @NotNull(message = "End Date is required")
    private LocalDate reservationEndDate;
    private String roomNumber;
    @NotNull(message = "Number of guests is required")
    private Integer guestsNumber;
    private Boolean advancePaid;

    public ReservationInsertDTO(LocalDate reservationStartDate, LocalDate reservationEndDate, Integer guestsNumber) {
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.guestsNumber = guestsNumber;
    }
}
