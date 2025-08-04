package com.levelup.erp.organisation.service;

import com.levelup.erp.organisation.models.HeadOffice;
import com.levelup.erp.organisation.models.Plant;
import com.levelup.erp.organisation.repository.HeadOfficeRepository;
import com.levelup.erp.organisation.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrganisationService {

    PlantRepository plantRepository;
    HeadOfficeRepository headOfficeRepository;
    public OrganisationService(PlantRepository plantRepository, HeadOfficeRepository headOfficeRepository) {
        this.plantRepository = plantRepository;
        this.headOfficeRepository = headOfficeRepository;

    }

    public List<Plant> plantList(){
        return plantRepository.findAll();
    }

    public List<HeadOffice> headOfficeList(){
        return headOfficeRepository.findAll();
    }


}
