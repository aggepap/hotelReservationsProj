package org.hotelbackend.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.*;
import org.hotelbackend.exceptions.AppGenericException;
import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;
import org.hotelbackend.exceptions.AppObjectInvalidArgumentException;
import org.hotelbackend.exceptions.AppObjectNotFoundException;
import org.hotelbackend.filters.Paginated;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.model.Room;
import org.hotelbackend.repository.ReservationRepository;
import org.hotelbackend.repository.ResidentRepository;
import org.hotelbackend.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ReservationService {

    @Inject
    private Mapper mapper;
    @Inject
    private ResidentService residentService;
    @Inject
    private ReservationRepository reservationRepository;
    @Inject
    private RoomService roomService;



    @Transactional
    public ReservationReadOnlyDTO addNewReservation(ReservationInsertDTO dto) throws AppObjectInvalidArgumentException, AppObjectAlreadyExistsException {
        ReservationInsertDTO basicReservationDataInsert = new ReservationInsertDTO(dto.getReservationStartDate(),dto.getReservationEndDate(),dto.getGuestsNumber());
        Reservation newReservation = reservationRepository.addReservation(basicReservationDataInsert);
        ResidentInsertDTO bookingResidentDTO = new ResidentInsertDTO(dto.getFirstname(), dto.getLastname(), dto.getVat(), dto.getIdNumber(),
                dto.getAddress(), dto.getPhoneNumber(), dto.getEmail(),dto.getBirthDate(),dto.getCountryCode(),dto.getGender(), newReservation.getId());

        Room bookingroom = Room.find("roomNumber",dto.getRoomNumber()).firstResult();

        Resident bookingResident = residentService.addNewResident(bookingResidentDTO);

        List<LocalDate> daysOfReservation = getDatesBetween(dto.getReservationStartDate(),dto.getReservationEndDate());
        boolean isAvailable = true;
        for(var date:daysOfReservation){
            if(roomService.checkAvailability(dto.getRoomNumber(),date)) {
                isAvailable = false;
            }
        }
        if(isAvailable){
            newReservation.addRoomToReservation(bookingroom);
            bookingroom.setIsAvailable(false);
            newReservation.addResidentToReservation(bookingResident);
        } else {
            throw new AppObjectInvalidArgumentException("Room", "Room " + bookingroom.getRoomNumber() + " " +
                    "is not available in selected dates");
        }

        return mapper.mapToReservationReadOnlyDTO(newReservation);
    }

    @Transactional
    public ReservationReadOnlyDTO updateReservation(ReservationUpdateDTO dto) throws AppObjectNotFoundException, AppObjectInvalidArgumentException {
        Reservation reservationToUpdate = reservationRepository.findById(dto.getId());
        Room reservationNewRoom = Room.find("roomNumber", dto.getRoomNumber()).firstResult();
        if(reservationToUpdate == null){
            throw new AppObjectNotFoundException("Reservation", "Reservation with id: " + dto.getId() + " was not found");
        }
        if(reservationNewRoom == null){
            throw new AppObjectNotFoundException("Room", "Room With number " + dto.getRoomNumber() + " was not found");
        }
        reservationToUpdate.setReservationStartDate(dto.getReservationStartDate());
        if(dto.getReservationEndDate().isBefore(reservationToUpdate.getReservationStartDate())){
            throw new AppObjectInvalidArgumentException("Reservation","End date cannot be earlier than start date");
        }

        List<LocalDate> daysOfReservation = getDatesBetween(dto.getReservationStartDate(),dto.getReservationEndDate());
        boolean isAvailable = true;
        for(var date:daysOfReservation){
            if(roomService.checkAvailability(dto.getRoomNumber(),date)) {
                isAvailable = false;
            }
        }
        if(isAvailable || reservationToUpdate.getRoom().getRoomNumber().equals(dto.getRoomNumber())){
            reservationToUpdate.setReservationEndDate(dto.getReservationEndDate());
            reservationToUpdate.setGuestsNumber(dto.getGuestsNumber());
            reservationToUpdate.getRoom().setIsAvailable(false);
            reservationToUpdate.setRoom(Room.find("roomNumber", dto.getRoomNumber()).firstResult());
        } else {
            throw new AppObjectInvalidArgumentException("Room", "Room " + dto.getRoomNumber() + " " +
                    "is not available in selected dates");
        }


        return mapper.mapToReservationReadOnlyDTO(reservationToUpdate);
    }

    @Transactional
    public Paginated<ReservationReadOnlyDTO> GetAllReservationsPaginated(int pageIndex, int pageSize, String sortedBy, LocalDate fromDate, LocalDate toDate, Boolean isActive){
        PanacheQuery<Reservation> query = null;
        if(isActive){
             query = Reservation.find(
                    "reservationStartDate >= ?1 and reservationStartDate <= ?2 and isActive = ?3",
                    Sort.by(sortedBy),
                    fromDate,
                    toDate,
                    isActive
            );
        }else{
            query = Reservation.find(
                    "reservationStartDate >= ?1 and reservationStartDate <= ?2",
                    Sort.by(sortedBy),
                    fromDate,
                    toDate
            );
        }


        long totalCount = query.count();
        List<ReservationReadOnlyDTO> data = query.page(pageIndex, pageSize).stream().map(mapper::mapToReservationReadOnlyDTO).toList();
        return new Paginated<>(data, totalCount);
    }

    @Transactional
    public List<ReservationReadOnlyDTO> GetAllReservations(String sortedBy, LocalDate fromDate, LocalDate toDate){
        PanacheQuery<Reservation> query = Reservation.find(
                "reservationStartDate >= ?1 and reservationStartDate <= ?2",
                Sort.by(sortedBy),
                fromDate,
                toDate
        );
        return query.stream().map(mapper::mapToReservationReadOnlyDTO).toList();

    }

    public List<ReservationReadOnlyDTO> getAllActiveReservations(String sortedBy){
        PanacheQuery<Reservation> query = Reservation.find("isActive", true,Sort.by(sortedBy));
        return query.stream().map(mapper::mapToReservationReadOnlyDTO).toList();
    }


    @Transactional
    public void CheckInReservation(Long reservationId) throws AppObjectNotFoundException, AppObjectInvalidArgumentException {
        Reservation reservation = reservationRepository.findById(reservationId);
        if(reservation == null){
            throw new AppObjectNotFoundException("Reservation","Reservation with id: " + reservationId + " not found");
        }
        if(reservation.getIsActive()){
            throw new AppObjectInvalidArgumentException("Reservation","Reservation: " + reservationId + " is already Checked in");
        }
            reservation.setIsActive(true);


    }

    public ReservationReadOnlyDTO getReservationByCode(String reservationCode){
       return mapper.mapToReservationReadOnlyDTO(reservationRepository.getReservationByResCode(reservationCode));
    }

    public ReservationReadOnlyDTO getReservationById(Long id) {
        return mapper.mapToReservationReadOnlyDTO(reservationRepository.findById(id));
    }

    private static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) throws AppObjectInvalidArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new AppObjectInvalidArgumentException("Reservation","Start date cannot be after end date.");
        }
        return startDate.datesUntil(endDate.plusDays(1)).toList();
    }

}


