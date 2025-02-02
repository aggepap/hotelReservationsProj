package org.hotelbackend.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.ReservationInsertDTO;
import org.hotelbackend.dto.ReservationReadOnlyDTO;
import org.hotelbackend.dto.ReservationUpdateDTO;
import org.hotelbackend.exceptions.AppObjectNotFoundException;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ReservationRepository implements  PanacheRepositoryBase<Reservation,Long> {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private Mapper mapper;
    @Inject
    private JPAStreamer jpaStreamer;


    @Transactional
    public Reservation addReservation(ReservationInsertDTO dto){
        Reservation insertedReservation = new Reservation();
        insertedReservation.setReservationStartDate(dto.getReservationStartDate());
        insertedReservation.setReservationEndDate(dto.getReservationEndDate());
        insertedReservation.setGuestsNumber(dto.getGuestsNumber());
        insertedReservation.persist();
        return insertedReservation;
    }


    public Reservation getReservationByResCode(String reservationCode){
        return find("reservationCode",reservationCode).firstResult();
    }
    public Reservation findById(Long id){
        return Reservation.findById(id);
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
