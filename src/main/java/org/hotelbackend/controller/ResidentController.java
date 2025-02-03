package org.hotelbackend.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.ResidentInsertDTO;
import org.hotelbackend.dto.ResidentReadOnlyDTO;
import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;
import org.hotelbackend.exceptions.AppObjectNotFoundException;
import org.hotelbackend.filters.Paginated;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Resident;
import org.hotelbackend.service.ReservationService;
import org.hotelbackend.service.ResidentService;
import org.jboss.logging.Logger;

import java.util.List;


@Path("/api/residents")
public class ResidentController {
    private static final Logger LOGGER = Logger.getLogger(ResidentController.class);

    @Inject
    private ResidentService residentService;
    @Inject
    private ReservationService reservationService;
    @Inject
    private Mapper mapper;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addResident(ResidentInsertDTO dto) throws AppObjectAlreadyExistsException {
        // Get Gender and CountryCode as Enums and set them to dto before adding new user
        Gender gender = Gender.valueOf(dto.getGender().name());
        dto.setGender(gender);
        CountryCode countryCode = CountryCode.valueOf(dto.getCountryCode().name());
        dto.setCountryCode(countryCode);


        //Set residents roomNumber if available
        Resident newResident = residentService.addNewResident(dto);
        if(dto.getReservationId() != null){
            Reservation reservation = reservationService.getReservationById(dto.getReservationId());
            reservation.addResidentToReservation(newResident);

        }
        //Map to readOnlyDTO to return this instead of a Resident
        ResidentReadOnlyDTO returnedResident  = mapper.mapToResidentReadOnlyDTO(newResident);
        return Response.status(Response.Status.CREATED).entity(returnedResident).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllResidentsPaginated(
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("10") int pageSize,
            @QueryParam("sortedBy")@DefaultValue("lastname") String sortedBy
    ){
        Paginated<ResidentReadOnlyDTO> residentsList = residentService.getAllResidentsPaginated(pageIndex,pageSize,sortedBy);
        return Response.status(Response.Status.OK).entity(residentsList).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllResidents(
            @QueryParam("sortedBy")@DefaultValue("lastname") String sortedBy
    ){
        List<ResidentReadOnlyDTO> residentsList = residentService.getAllResidents(sortedBy);
        return Response.status(Response.Status.OK).entity(residentsList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getResidentById(
            @PathParam("id") Long id
    ) throws AppObjectNotFoundException {
        Resident resident = residentService.getById(id);
        if(resident == null){
        }
        ResidentReadOnlyDTO residentToReturn = mapper.mapToResidentReadOnlyDTO( residentService.getById(id));
        return Response.status(Response.Status.OK).entity(residentToReturn).build();
    }

}
