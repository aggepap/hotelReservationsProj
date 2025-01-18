package org.hotelbackend.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.RoomInsertDTO;
import org.hotelbackend.dto.RoomReadOnlyDTO;
import org.hotelbackend.model.Reservation;
import org.hotelbackend.model.Room;
import org.hotelbackend.model.Room_;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private Mapper mapper;
    @Inject
    private JPAStreamer jpaStreamer;
    private static final int PAGE_SIZE = 5;


    @Transactional
    public RoomReadOnlyDTO addRoom(RoomInsertDTO dto){
        Room insertedRoom = new Room(null, dto.getRoomNumber(),
                dto.getFloor(), dto.getCapacity(), dto.getHasSeaView(), null);
        this.entityManager.persist(insertedRoom);
        return mapper.matToRoomReadOnlyDTO(insertedRoom);
    }

    public Optional<RoomReadOnlyDTO> getRoomByNumber(Integer roomNumber){
   return jpaStreamer.stream(Room.class)
           .filter(room -> room.getRoomNumber().equals(roomNumber))
           .findFirst()
           .map(mapper::matToRoomReadOnlyDTO);
    }

    public List<RoomReadOnlyDTO> getRoomsByFloor(Integer floor){
        return jpaStreamer.stream(Room.class)
                .filter(room->room.getFloor().equals(floor)).map(mapper::matToRoomReadOnlyDTO)
                .toList();
    }

    public List<RoomReadOnlyDTO> getRoomsWithSeaView(long page){
        return jpaStreamer.stream(Room.class)
                .filter(room->room.getHasSeaView().equals(true)).map(mapper::matToRoomReadOnlyDTO)
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .toList();
    }


}
