package org.hotelbackend.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.ReservationInsertDTO;
import org.hotelbackend.dto.ReservationReadOnlyDTO;

import org.hotelbackend.dto.ReservationUpdateDTO;
import org.hotelbackend.dto.ResidentReadOnlyDTO;
import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;
import org.hotelbackend.exceptions.AppObjectInvalidArgumentException;
import org.hotelbackend.exceptions.AppObjectNotFoundException;
import org.hotelbackend.filters.Paginated;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.service.ReservationService;
import org.hotelbackend.service.ResidentService;
import org.jboss.logging.Logger;

import java.time.LocalDate;

@Path("/api/reservations")
public class ReservationController {
    public static final Logger LOGGER = Logger.getLogger(ReservationController.class);

    @Inject
    private ReservationService reservationService;
    @Inject
    private ResidentService residentService;
    @Inject
    private Mapper mapper;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReservation(ReservationInsertDTO dto) throws AppObjectAlreadyExistsException, AppObjectNotFoundException, AppObjectInvalidArgumentException {
        ReservationReadOnlyDTO newReservation = reservationService.addNewReservation(dto);
        return Response.status(Response.Status.CREATED).entity(newReservation).build();
    }

//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getByCode(@QueryParam("resCode") String reservationCode) {
//        ReservationReadOnlyDTO fetched = reservationService.getReservationByCode(reservationCode);
//        return Response.status(Response.Status.OK).entity(fetched).build();
//    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        ReservationReadOnlyDTO fetched = mapper.mapToReservationReadOnlyDTO(reservationService.getReservationById(id));
        return Response.status(Response.Status.OK).entity(fetched).build();
    }



    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReservationsPaginatedByDate(
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("10") int pageSize,
            @QueryParam("sortedBy") @DefaultValue("id") String sortedBy,
            @QueryParam("fromDate") @DefaultValue("1950-01-01") LocalDate fromDate,
            @QueryParam("toDate") @DefaultValue("2150-01-01") LocalDate toDate,
            @QueryParam("isActive") @DefaultValue("") Boolean isActive

    ) {
        Paginated<ReservationReadOnlyDTO> GetAllReservations = reservationService.GetAllReservationsPaginated(pageIndex, pageSize, sortedBy, fromDate, toDate, isActive);
        return Response.status(Response.Status.OK).entity(GetAllReservations).build();
    }

    @PATCH
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReservation(ReservationUpdateDTO dto) throws AppObjectNotFoundException, AppObjectInvalidArgumentException {

            ReservationReadOnlyDTO updatedReservation = reservationService.updateReservation(dto);
            return Response.status(Response.Status.OK).entity(updatedReservation).build();
    }

    @PATCH
    @Path("/{reservationId}/add-resident/{residentId}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addResidentToReservation (@PathParam("reservationId") Long reservationId,
                                              @PathParam("residentId")  Long residentId) throws AppObjectNotFoundException {
        Reservation reservation = reservationService.getReservationById(reservationId);
        Resident resident = residentService.getById(residentId);
        reservation.addResidentToReservation(resident);
        ReservationReadOnlyDTO reservationToReturn = mapper.mapToReservationReadOnlyDTO(reservation);
        return Response.status(Response.Status.OK).entity(reservationToReturn).build();
    }
    @PATCH
    @Path("/{reservationId}/remove-resident/{residentId}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeResident (@PathParam("reservationId") Long reservationId,
                                              @PathParam("residentId")  Long residentId) throws AppObjectNotFoundException {
        Reservation reservation = reservationService.getReservationById(reservationId);
        Resident resident = residentService.getById(residentId);
        reservation.removeResidentFromReservation(resident);
        ReservationReadOnlyDTO reservationToReturn = mapper.mapToReservationReadOnlyDTO(reservation);
        return Response.status(Response.Status.OK).entity(reservationToReturn).build();
    }


    @PATCH
    @Path("/checkin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkInReservation(
            @QueryParam("reservationId") Long reservationId
    ){
        try {
            reservationService.CheckInReservation(reservationId);
            return Response.status(Response.Status.OK).entity("Reservation was succefully checked in").build();
        } catch (AppObjectNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Reservation with id " + reservationId + "was not found").build();
        } catch (AppObjectInvalidArgumentException e) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("Reservation with id " + reservationId + " is already checked in").build();

        }

    }
}
