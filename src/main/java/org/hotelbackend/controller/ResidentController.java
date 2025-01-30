package org.hotelbackend.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;
import org.hotelbackend.dto.ResidentInsertDTO;
import org.hotelbackend.dto.ResidentReadOnlyDTO;
import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;
import org.hotelbackend.filters.Paginated;
import org.hotelbackend.service.ResidentService;
import org.jboss.logging.Logger;

import java.util.List;


@Path("/api/residents")
public class ResidentController {
    private static final Logger LOGGER = Logger.getLogger(ResidentController.class);

    @Inject
    private ResidentService residentService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addResident(ResidentInsertDTO dto) throws AppObjectAlreadyExistsException {
         Gender gender = Gender.valueOf(dto.getGender().name());
         dto.setGender(gender);
        CountryCode countryCode = CountryCode.valueOf(dto.getCountryCode().name());
        dto.setCountryCode(countryCode);

        ResidentReadOnlyDTO newResident = residentService.addNewResident(dto);
        return Response.status(Response.Status.CREATED).entity(newResident).build();
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

}
