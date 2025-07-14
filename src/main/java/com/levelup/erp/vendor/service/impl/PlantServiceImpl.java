package com.levelup.erp.vendor.service.impl;

import com.levelup.erp.vendor.model.Plant;
import com.levelup.erp.vendor.repository.PlantRepository;
import com.levelup.erp.vendor.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantServiceImpl(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }
}
