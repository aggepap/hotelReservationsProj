package org.hotelbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInsertDTO {
    private Integer roomNumber;
    private Integer floor;
    private Integer capacity;
    private Boolean hasSeaView;
}
