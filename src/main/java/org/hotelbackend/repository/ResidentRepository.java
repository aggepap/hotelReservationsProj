package org.hotelbackend.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
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
public class ResidentRepository implements PanacheRepositoryBase<Resident,Long> {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private Mapper mapper;
    @Inject
    private JPAStreamer jpaStreamer;


    @Transactional
    public Resident addResident(ResidentInsertDTO dto){
        Resident insertedResident = mapper.mapToResidentEntity(dto);
        insertedResident.persist();
        return insertedResident;
    }

    public Resident findByVat(String vat){
        return find("vat",vat).firstResult();
    }
    public Resident findByIdNumber(String idNumber){
        return find("idNumber",idNumber).firstResult();
    }
}
