package org.hotelbackend.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hotelbackend.dto.RoomInsertDTO;
import org.hotelbackend.dto.RoomReadOnlyDTO;
import org.hotelbackend.repository.RoomRepository;

import java.util.List;

@ApplicationScoped

public class RoomService {

    @Inject
    private RoomRepository roomRepository;


    public RoomReadOnlyDTO addNewRoom(RoomInsertDTO dto){
      return this.roomRepository.addRoom(dto);
    }

    public RoomReadOnlyDTO findRoomByNumber(Integer roomNumber){
        return this.roomRepository.getRoomByNumber(roomNumber).orElseThrow();
    }

    public List<RoomReadOnlyDTO> findRoomsByFloor(Integer floor){
        return this.roomRepository.getRoomsByFloor(floor);
    }
    public List<RoomReadOnlyDTO> findRoomsWithSeaView(long page){
       return this.roomRepository.getRoomsWithSeaView(page);
    }
}
