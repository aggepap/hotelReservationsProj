package org.hotelbackend.core;


import jakarta.enterprise.context.ApplicationScoped;
import org.hotelbackend.dto.*;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.model.Room;

@ApplicationScoped
public class Mapper {

    public Room mapToRoomEntity(RoomInsertDTO dto){
        return new Room(null, dto.getRoomNumber(), dto.getFloor(), dto.getCapacity(), dto.getHasSeaView(), null);
    }
    public RoomReadOnlyDTO matToRoomReadOnlyDTO(Room room){
        return new RoomReadOnlyDTO(room.getId(), room.getRoomNumber(), room.getFloor(), room.getCapacity(), room.getHasSeaView());
    }

    public Reservation mapToReservationEntity(ReservationInsertDTO dto){
        return new Reservation(null, null, dto.getReservationBookedDate(), dto.getReservationStartDate(),
                dto.getReservationEndDate(), dto.getAdvancePaid(),null, null, null);
    }
    public ReservationReadOnlyDTO mapToReservationReadOnlyDTO(Reservation reservation){
        return new ReservationReadOnlyDTO(reservation.getReservationCode(),reservation.getReservationBookedDate(),
                reservation.getReservationStartDate(),reservation.getReservationEndDate(),reservation.getAdvancePaid(),
                reservation.getIsActive(),reservation.getRoom().getId(),reservation.getResidents());
    }
    public Resident mapToResidentEntity(ResidentInsertDTO dto){
        return new Resident(null,dto.getFirstname(), dto.getLastname(), dto.getVat(), dto.getIdNumber(),
                dto.getBirthDate(),dto.getCountryCode(),dto.getGender(),null,null);
    }
    public ResidentReadOnlyDTO mapToResidentReadOnlyDTO(Resident resident){
        return new ResidentReadOnlyDTO(resident.getId(), resident.getFirstname(),resident.getLastname(),
                resident.getVat(), resident.getIdNumber(), resident.getBirthDate(),resident.getCountryCode(),
                resident.getGender(),resident.getIsAdult(),resident.getReservation());
    }
}
