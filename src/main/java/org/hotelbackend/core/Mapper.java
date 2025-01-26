package org.hotelbackend.core;


import jakarta.enterprise.context.ApplicationScoped;
import org.hotelbackend.dto.*;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.model.Room;

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

        return new ReservationReadOnlyDTO(reservation.getReservationCode(),reservation.getReservationBookedDate(),
                reservation.getReservationStartDate(),reservation.getReservationEndDate(),reservation.getGuestsNumber(),reservation.getAdvancePaid(),
                reservation.getIsActive(),reservation.getRoom().getId(),reservation.getResidents());
    }
    public Resident mapToResidentEntity(ResidentInsertDTO dto){
        return new Resident(null,dto.getFirstname(), dto.getLastname(), dto.getVat(), dto.getIdNumber(),
                dto.getBirthDate(), dto.getAddress(), dto.getPhoneNumber(),dto.getEmail(),dto.getCountryCode(),dto.getGender(),null,null,null,null);
    }
    public ResidentReadOnlyDTO mapToResidentReadOnlyDTO(Resident resident){
        return new ResidentReadOnlyDTO(resident.getId(), resident.getFirstname(),resident.getLastname(),
                resident.getVat(), resident.getIdNumber(), resident.getBirthDate(),resident.getCountryCode().name(),
                resident.getAddress(),resident.getEmail(),resident.getPhoneNumber(),resident.getGender().name(),resident.getIsAdult(),null,resident.getReservation());
    }
}
