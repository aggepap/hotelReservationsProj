package org.hotelbackend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;
import org.hotelbackend.model.Reservation;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentReadOnlyDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String vat;
    private String idNumber;
    private LocalDate birthDate;
    private CountryCode countryCode;
    private Gender gender;
    private Boolean isAdult;
    private Reservation reservation;
}
