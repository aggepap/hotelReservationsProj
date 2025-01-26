package org.hotelbackend.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.RoomInsertDTO;
import org.hotelbackend.dto.RoomReadOnlyDTO;
import org.hotelbackend.filters.Paginated;
import org.hotelbackend.model.Room;
import org.hotelbackend.repository.RoomRepository;

import java.util.List;

@ApplicationScoped

public class RoomService {

    @Inject
    private RoomRepository roomRepository;
    @Inject
    private Mapper mapper;


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


    public List<RoomReadOnlyDTO> findRoomsByFloor(Integer floor){
        return this.roomRepository.getRoomsByFloor(floor);
    }
    public List<RoomReadOnlyDTO> findRoomsWithSeaView(long page){
       return this.roomRepository.getRoomsWithSeaView(page);
    }
}
