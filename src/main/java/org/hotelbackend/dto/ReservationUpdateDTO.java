package org.hotelbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateDTO {
    private Long id;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private String roomNumber;
    private Integer guestsNumber;
    private Boolean advancePaid;
}
