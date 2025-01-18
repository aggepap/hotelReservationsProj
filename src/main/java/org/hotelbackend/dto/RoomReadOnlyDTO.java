package org.hotelbackend.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReadOnlyDTO {
    private Long id;
    private Integer roomNumber;
    private Integer floor;
    private Integer capacity;
    private Boolean hasSeaView;
}
