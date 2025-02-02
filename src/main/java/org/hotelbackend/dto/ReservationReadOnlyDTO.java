package org.hotelbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hotelbackend.model.Resident;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationReadOnlyDTO {
    private Long id;
    private String reservationCode;
    private LocalDateTime reservationBookedDate;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;
    private Integer guestsNumber;
    private Boolean advancePaid;
    private Boolean isActive;
    private Long roomId;
    private String roomNumber;
    private Set<ResidentReadOnlyDTO>residents;
}
