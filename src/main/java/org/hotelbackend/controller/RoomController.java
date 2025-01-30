package org.hotelbackend.controller;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.expressly.parser.BooleanNode;
import org.hotelbackend.dto.RoomInsertDTO;
import org.hotelbackend.dto.RoomReadOnlyDTO;
import org.hotelbackend.filters.Paginated;
import org.hotelbackend.model.Room;
import org.hotelbackend.service.RoomService;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.util.List;


@Path("/api/rooms")
public class RoomController {

    private static final Logger LOGGER = Logger.getLogger(RoomController.class);

    @Inject
    private RoomService roomService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoom( RoomInsertDTO dto) {
        RoomReadOnlyDTO newRoom = roomService.addNewRoom(dto);
        return Response.status(Response.Status.CREATED).entity(newRoom).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms(
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("10") int pageSize
    ){
        Paginated<RoomReadOnlyDTO> allRooms = roomService.getAllRooms(pageIndex,pageSize);
        return Response.status(Response.Status.OK).entity(allRooms).build();
    }

    @GET
    @Path("/checkdates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkAvailability(
            @QueryParam("roomNumber") String roomNumber,
            @QueryParam("date") LocalDate date
    ){
      Boolean isAvailable =  roomService.checkAvailability(roomNumber,date);
        return Response.status(Response.Status.OK).entity(isAvailable).build();
    }
}