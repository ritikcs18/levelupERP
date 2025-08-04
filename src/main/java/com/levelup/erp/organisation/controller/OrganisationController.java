package com.levelup.erp.organisation.controller;

import com.levelup.erp.organisation.models.HeadOffice;
import com.levelup.erp.organisation.models.Plant;
import com.levelup.erp.organisation.service.OrganisationService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganisationController {

    OrganisationService organisationService;
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;

    }

    public List<Plant> getAllPlants(){
        return organisationService.plantList();
    }

    public List<HeadOffice> getAllHeadOffices(){
        return organisationService.headOfficeList();
    }


}
