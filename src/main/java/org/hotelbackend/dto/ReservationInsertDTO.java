package org.hotelbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationInsertDTO {
    private LocalDateTime reservationBookedDate;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Boolean advancePaid;

}
