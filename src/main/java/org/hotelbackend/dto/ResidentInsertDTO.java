package org.hotelbackend.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentInsertDTO {

    @NotNull(message = "Firstname is required")
    private String firstname;
    @NotNull(message = "Lastname is required")
    private String lastname;
    private String vat;
    private String idNumber;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private CountryCode countryCode;
    private Gender gender;
    private Long reservationId;

}
