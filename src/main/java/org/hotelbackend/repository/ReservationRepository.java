package org.hotelbackend.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.ReservationInsertDTO;
import org.hotelbackend.dto.ReservationReadOnlyDTO;
import org.hotelbackend.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private Mapper mapper;
    @Inject
    private JPAStreamer jpaStreamer;


    @Transactional
    public ReservationReadOnlyDTO addReservation(ReservationInsertDTO dto){
        Reservation insertedReservation = new Reservation(null, null, dto.getReservationBookedDate(),
                dto.getReservationStartDate(),dto.getReservationEndDate(),
                dto.getAdvancePaid(),true, null, null);
        this.entityManager.persist(insertedReservation);
        return mapper.mapToReservationReadOnlyDTO(insertedReservation);
    }


    public Optional<ReservationReadOnlyDTO> getReservationByResCode(String reservationCode){
        return jpaStreamer.stream(Reservation.class)
                .filter(reservation -> reservation.getReservationCode().equals(reservationCode))
                .findFirst()
                .map(mapper::mapToReservationReadOnlyDTO);
    }

    public List<ReservationReadOnlyDTO> getReservationsByRoomAndDates(Integer roomNumber, LocalDate startDate, LocalDate endDate){
        return jpaStreamer.stream(Reservation.class)
                .filter(reservation -> reservation.getRoom().getRoomNumber().equals(roomNumber)
                && reservation.getReservationStartDate().isAfter(startDate)
                && reservation.getReservationEndDate().isBefore(endDate))
                .map(mapper::mapToReservationReadOnlyDTO)
                .toList();
    }

    public List<ReservationReadOnlyDTO> getAllReservationsByDates(LocalDate startDate, LocalDate endDate){
        return jpaStreamer.stream(Reservation.class)
                .filter(reservation -> reservation.getReservationStartDate().isAfter(startDate)
                && reservation.getReservationEndDate().isBefore(endDate))
                .map(mapper::mapToReservationReadOnlyDTO)
                .toList();
    }
}
