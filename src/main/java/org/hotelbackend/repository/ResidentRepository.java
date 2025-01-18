package org.hotelbackend.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.ResidentInsertDTO;
import org.hotelbackend.dto.ResidentReadOnlyDTO;
import org.hotelbackend.model.Resident;

@ApplicationScoped
public class ResidentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private Mapper mapper;
    @Inject
    private JPAStreamer jpaStreamer;


    @Transactional
    public ResidentReadOnlyDTO addResident(ResidentInsertDTO dto){
        Resident insertedResident = new Resident(null, dto.getFirstname(), dto.getLastname(), dto.getVat(),
                dto.getIdNumber(),dto.getBirthDate(),dto.getCountryCode(),
                dto.getGender(),null, null);
        this.entityManager.persist(insertedResident);
        return mapper.mapToResidentReadOnlyDTO(insertedResident);
    }
}
