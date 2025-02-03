package org.hotelbackend.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.RoomInsertDTO;
import org.hotelbackend.dto.RoomReadOnlyDTO;
import org.hotelbackend.filters.Paginated;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Room;
import org.hotelbackend.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@ApplicationScoped

public class RoomService {

    @Inject
    private RoomRepository roomRepository;
    @Inject
    private Mapper mapper;

@Transactional
    public RoomReadOnlyDTO addNewRoom(RoomInsertDTO dto){
        System.out.println(dto.getHasSeaView());
        return this.roomRepository.addRoom(dto);
    }

    public Room findRoomByNumber(String roomNumber){
        return this.roomRepository.getRoomEntityByNumber(roomNumber);
    }

    public Paginated<RoomReadOnlyDTO> getAllRooms(int pageIndex, int pageSize){
        PanacheQuery<Room> query = Room.findAll(Sort.by("roomNumber"));
        long totalCount = query.count();
        List<RoomReadOnlyDTO> data = query.page(pageIndex, pageSize).stream().map(mapper::mapToRoomReadOnlyDTO).toList();
        return new Paginated<>(data, totalCount);

    }

    public Boolean checkAvailability(String roomNumber, LocalDate date){
        boolean isAvailable = false;
        Room selectedRoom = Room.find("roomNumber", roomNumber).firstResult();
        Set<Reservation> roomReservations = selectedRoom.getReservations();

        for (var reservation: roomReservations ){
            if ((date.isAfter(reservation.getReservationStartDate()) || date.isEqual(reservation.getReservationStartDate()))
                    && (date.isBefore(reservation.getReservationEndDate()) || date.isEqual(reservation.getReservationEndDate()))) {
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }

    public List<RoomReadOnlyDTO> findRoomsByFloor(Integer floor){
        return this.roomRepository.getRoomsByFloor(floor);
    }
    public List<RoomReadOnlyDTO> findRoomsWithSeaView(long page){
       return this.roomRepository.getRoomsWithSeaView(page);
    }
}
