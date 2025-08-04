package com.levelup.erp.material.controller;


import com.levelup.erp.material.dto.FinishedMaterialDTO;
import com.levelup.erp.material.service.FinishedMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialAPI {

    private FinishedMaterialService finishedMaterialService;

    public MaterialAPI(FinishedMaterialService finishedMaterialService) {
        this.finishedMaterialService = finishedMaterialService;
    }

    @GetMapping("/submaterials")
    public ResponseEntity<List<FinishedMaterialDTO>> getAllMaterials() {
        List<FinishedMaterialDTO> subMaterials = finishedMaterialService.getAllMaterials();
        return ResponseEntity.ok(subMaterials);
    }

}
