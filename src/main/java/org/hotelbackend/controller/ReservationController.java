package org.hotelbackend.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;
import org.hotelbackend.dto.ReservationInsertDTO;
import org.hotelbackend.dto.ReservationReadOnlyDTO;

import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;
import org.hotelbackend.exceptions.AppObjectNotFoundException;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.service.ReservationService;
import org.jboss.logging.Logger;

import java.util.Date;

@Path("/api/reservations")
public class ReservationController {
    public static final Logger LOGGER = Logger.getLogger(ReservationController.class);

    @Inject
    private ReservationService reservationService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReservation(ReservationInsertDTO dto) throws AppObjectAlreadyExistsException, AppObjectNotFoundException {
        ReservationReadOnlyDTO newReservation = reservationService.addNewReservation(dto);
        return Response.status(Response.Status.CREATED).entity(newReservation).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCode(  @QueryParam("resCode") String reservationCode){
        Reservation fetched = reservationService.getByCode(reservationCode);

        return  Response.status(Response.Status.OK).entity(fetched).build();
    }
}
