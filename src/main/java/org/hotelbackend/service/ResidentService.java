package org.hotelbackend.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hotelbackend.dto.ResidentInsertDTO;
import org.hotelbackend.dto.ResidentReadOnlyDTO;
import org.hotelbackend.repository.ResidentRepository;

@ApplicationScoped
public class ResidentService {

    @Inject
    private ResidentRepository residentRepository;

    public ResidentReadOnlyDTO addNewResident(ResidentInsertDTO dto){
        return residentRepository.addResident(dto);
    }

}
