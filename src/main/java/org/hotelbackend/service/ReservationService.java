package org.hotelbackend.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.*;
import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;
import org.hotelbackend.exceptions.AppObjectNotFoundException;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.model.Room;
import org.hotelbackend.repository.ReservationRepository;
import org.hotelbackend.repository.ResidentRepository;
import org.hotelbackend.repository.RoomRepository;

import java.util.Optional;

@ApplicationScoped
public class ReservationService {

    @Inject
    private Mapper mapper;
    @Inject
    private ResidentRepository residentRepository;
    @Inject
    private ReservationRepository reservationRepository;
    @Inject
    private RoomRepository roomRepository;



    @Transactional
    public ReservationReadOnlyDTO addNewReservation(ReservationInsertDTO dto) {


        ReservationInsertDTO basicReservationDataInsert = new ReservationInsertDTO(dto.getReservationStartDate(),dto.getReservationEndDate(),dto.getGuestsNumber());
        ResidentInsertDTO bookingResidentDTO = new ResidentInsertDTO(dto.getFirstname(), dto.getLastname(), dto.getVat(), dto.getIdNumber(),
                dto.getAddress(), dto.getPhoneNumber(), dto.getEmail(),dto.getBirthDate(),dto.getCountryCode(),dto.getGender());

        Room bookingroom = roomRepository.getRoomEntityByNumber(dto.getRoomNumber());

        Reservation newReservation = reservationRepository.addReservation(basicReservationDataInsert);
        Resident bookingResident = residentRepository.addResident(bookingResidentDTO);
        newReservation.addRoomToReservation(bookingroom);
        newReservation.addResidentToReservation(bookingResident);
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////");
        System.out.println(bookingResident);
        System.out.println(newReservation.getResidents());
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////");

        return mapper.mapToReservationReadOnlyDTO(newReservation);
    }

    public Reservation getByCode(String reservationCode){
       return reservationRepository.getReservationByResCode(reservationCode);
    }
}
