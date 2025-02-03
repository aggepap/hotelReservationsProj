package org.hotelbackend.core;


import jakarta.enterprise.context.ApplicationScoped;
import org.hotelbackend.dto.*;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.model.Room;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class Mapper {

    public Room mapToRoomEntity(RoomInsertDTO dto){
        return new Room(null, dto.getRoomNumber(), dto.getFloor(), dto.getCapacity(), dto.getHasSeaView(), null,  null);
    }
    public RoomReadOnlyDTO mapToRoomReadOnlyDTO(Room room){
        return new RoomReadOnlyDTO(room.getId(), room.getRoomNumber(), room.getFloor(), room.getCapacity(), room.getHasSeaView(), room.getIsAvailable());
    }

    public Reservation mapToReservationEntity(ReservationInsertDTO dto){
        return new Reservation(null, null, null, dto.getReservationStartDate(),
                dto.getReservationEndDate(),dto.getGuestsNumber(), dto.getAdvancePaid(),null, null,null, null, null);
    }
    public ReservationReadOnlyDTO mapToReservationReadOnlyDTO(Reservation reservation){
        Long roomId;
        String roomNumber;
        if(reservation.getRoom() != null){
            roomId = reservation.getRoom().getId();
            roomNumber = reservation.getRoom().getRoomNumber();
        }else{
            roomId =null;
            roomNumber = null;
        }

        Set<ResidentReadOnlyDTO> residentDTOs = null;
        if (reservation.getResidents() != null) {
            residentDTOs = reservation.getResidents().stream()
                    .map(this::mapToResidentReadOnlyDTO)
                    .collect(Collectors.toSet());
        }

        return new ReservationReadOnlyDTO(reservation.getId(),reservation.getReservationCode(),reservation.getReservationBookedDate(),
                reservation.getReservationStartDate(),reservation.getReservationEndDate(),reservation.getGuestsNumber(),reservation.getAdvancePaid(),
                reservation.getIsActive(),roomId,roomNumber,residentDTOs);
    }
    public Resident mapToResidentEntity(ResidentInsertDTO dto){
        return new Resident(null,dto.getFirstname(), dto.getLastname(), dto.getVat(), dto.getIdNumber(),
                dto.getBirthDate(), dto.getAddress(), dto.getPhoneNumber(),dto.getEmail(),dto.getCountryCode(),dto.getGender(),null,null,null,null);
    }
    public ResidentReadOnlyDTO mapToResidentReadOnlyDTO(Resident resident){
        String reservationCode= null;
        String residentRoomNumber= null;
        if (resident.getReservation() != null) {
            reservationCode = resident.getReservation().getReservationCode();

            // Check if Room exists within Reservation
            if (resident.getReservation().getRoom() != null) {
                residentRoomNumber = resident.getReservation().getRoom().getRoomNumber();
            }
        }

        return new ResidentReadOnlyDTO(resident.getId(), resident.getFirstname(),resident.getLastname(),
                resident.getVat(), resident.getIdNumber(), resident.getBirthDate(),resident.getCountryCode().name(),
                resident.getAddress(),resident.getEmail(),resident.getPhoneNumber(),resident.getGender().name(),resident.getIsAdult(),residentRoomNumber,reservationCode);
    }
}
