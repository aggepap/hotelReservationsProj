package org.hotelbackend;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.hotelbackend.core.CountryCode;
import org.hotelbackend.core.Gender;
import org.hotelbackend.dto.ResidentInsertDTO;
import org.hotelbackend.dto.ResidentReadOnlyDTO;
import org.hotelbackend.dto.RoomInsertDTO;
import org.hotelbackend.model.Resident;
import org.hotelbackend.service.ResidentService;
import org.hotelbackend.service.RoomService;

import java.time.LocalDate;

@Path("/hello")

public class GreetingResource {

    @Inject
    private RoomService roomService;
    @Inject
    ResidentService residentService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
//        RoomInsertDTO newRoom = new RoomInsertDTO(101,1, 3, false);
//        roomService.addNewRoom(newRoom);
//        roomService.findRoomByNumber(102);
        System.out.println(roomService.findRoomByNumber(101));
        System.out.println("Rooms on floor " + 1 +  roomService.findRoomsByFloor(1));
        System.out.println("Rooms with Sea view " + roomService.findRoomsWithSeaView(0));


         ResidentInsertDTO newUser = new ResidentInsertDTO("Andriana","Papanagiotou","125971624","F254215", LocalDate.of(2022,9,25), CountryCode.GRC, Gender.Female);
        residentService.addNewResident(newUser);


        return "Hello from RESTEasy Reactive";
    }
}
