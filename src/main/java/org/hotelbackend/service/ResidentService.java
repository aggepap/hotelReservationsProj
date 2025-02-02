package org.hotelbackend.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hotelbackend.core.Mapper;
import org.hotelbackend.dto.ResidentInsertDTO;
import org.hotelbackend.dto.ResidentReadOnlyDTO;
import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;

import org.hotelbackend.filters.Paginated;
import org.hotelbackend.model.Resident;
import org.hotelbackend.repository.ResidentRepository;

import java.util.List;

@ApplicationScoped
public class ResidentService {

    @Inject
    private Mapper mapper;
    @Inject
    private ResidentRepository residentRepository;


    public Resident addNewResident(ResidentInsertDTO dto) throws AppObjectAlreadyExistsException {
        if(residentRepository.findByVat(dto.getVat()) != null){
            throw new AppObjectAlreadyExistsException("Resident", "Resident with VAT: " + dto.getVat() + " already exists");
        }
        if(residentRepository.findByIdNumber(dto.getIdNumber()) != null){
            throw new AppObjectAlreadyExistsException("Resident", "Resident with Id Number: " + dto.getIdNumber() + " already exists");
        }
        return residentRepository.addResident(dto);
    }

    public Paginated<ResidentReadOnlyDTO> getAllResidentsPaginated(int pageIndex, int pageSize, String sortedBy ){
        PanacheQuery<Resident> query = Resident.findAll(Sort.by(sortedBy));
        long totalCount = query.count();
        List<ResidentReadOnlyDTO> data = query.page(pageIndex, pageSize).stream().map(mapper::mapToResidentReadOnlyDTO).toList();
        return new Paginated<>(data, totalCount);

    }
    public List<ResidentReadOnlyDTO> getAllResidents(String sortedBy ){
        PanacheQuery<Resident> query = Resident.findAll(Sort.by(sortedBy));
        return query.stream().map(mapper::mapToResidentReadOnlyDTO).toList();

    }

}
