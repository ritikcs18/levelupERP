package com.levelup.erp.material.service;
import com.levelup.erp.material.dto.FinishedMaterialDTO;
import com.levelup.erp.material.dto.Mapper;
import com.levelup.erp.material.models.FinishedMaterial;
import com.levelup.erp.material.repository.FinishedMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class FinishedMaterialService {

    private final FinishedMaterialRepository finishedMaterialRepository;

    @Autowired
    public FinishedMaterialService(FinishedMaterialRepository finishedMaterialRepository) {
        this.finishedMaterialRepository = finishedMaterialRepository;
    }

    public List<FinishedMaterialDTO> getAllMaterials() {
        List<FinishedMaterial> entities = finishedMaterialRepository.findAll();
        return entities.stream().map(Mapper::fromEntity).collect(Collectors.toList());
    }

    public void saveVendorFromDTO(FinishedMaterialDTO finishedMaterialDTO){
        FinishedMaterial finishedMaterial = Mapper.toEntity(finishedMaterialDTO);
        finishedMaterialRepository.save(finishedMaterial);
    }



}
