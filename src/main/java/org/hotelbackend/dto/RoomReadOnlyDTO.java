package org.hotelbackend.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReadOnlyDTO {
    private Long id;
    private String roomNumber;
    private String floor;
    private Integer capacity;
    private Boolean hasSeaView;
    private Boolean isAvailable;
}
