package org.hotelbackend.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String firstname;
    private String lastname;
    private String vat;
    private String idNumber;
    private LocalDate birthDate;
    private CountryCode countryCode;
    private Gender gender;
}
