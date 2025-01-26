package org.hotelbackend.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.RoomInsertDTO;
import org.hotelbackend.dto.RoomReadOnlyDTO;
import org.hotelbackend.model.Room;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class RoomRepository implements PanacheRepositoryBase<Room,Long> {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private Mapper mapper;
    @Inject
    private JPAStreamer jpaStreamer;
    private static final int PAGE_SIZE = 5;


    @Transactional
    public RoomReadOnlyDTO addRoom(RoomInsertDTO dto){
        Room insertedRoom = mapper.mapToRoomEntity(dto);
        insertedRoom.persist();
        return mapper.mapToRoomReadOnlyDTO(insertedRoom);
    }

    public RoomReadOnlyDTO getRoomByNumber(String roomNumber){
        return mapper.mapToRoomReadOnlyDTO(find("roomNumber",roomNumber).firstResult());
    }

    public Room getRoomEntityByNumber(String roomNumber) {
        return find("roomNumber",roomNumber).firstResult();
    }


    public List<RoomReadOnlyDTO> getRoomsByFloor(Integer floor){
        return jpaStreamer.stream(Room.class)
                .filter(room->room.getFloor().equals(floor)).map(mapper::mapToRoomReadOnlyDTO)
                .toList();
    }

    public List<RoomReadOnlyDTO> getRoomsWithSeaView(long page){
        return jpaStreamer.stream(Room.class)
                .filter(room->room.getHasSeaView().equals(true)).map(mapper::mapToRoomReadOnlyDTO)
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .toList();
    }




}
