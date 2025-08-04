package com.levelup.erp.po.service;

import com.levelup.erp.organisation.models.HeadOffice;
import com.levelup.erp.organisation.models.Plant;
import com.levelup.erp.organisation.repository.HeadOfficeRepository;
import com.levelup.erp.organisation.repository.PlantRepository;
import com.levelup.erp.po.dto.PurchaseOrderDTO;
import com.levelup.erp.po.dto.PurchaseOrderMapper;
import com.levelup.erp.po.models.PurchaseOrder;
import com.levelup.erp.po.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final HeadOfficeRepository headOfficeRepository;
    private final PlantRepository plantRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
                                HeadOfficeRepository headOfficeRepository,
                                PlantRepository plantRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.headOfficeRepository = headOfficeRepository;
        this.plantRepository = plantRepository;
    }


    public PurchaseOrder createPurchaseOrder(PurchaseOrderDTO dto) {
        HeadOffice headOffice = headOfficeRepository.findById(dto.getBillToId())
                .orElseThrow(() -> new RuntimeException("HeadOffice not found"));

        Plant plant = plantRepository.findById(dto.getShipToPlantId())
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        PurchaseOrder po = PurchaseOrderMapper.toEntity(dto, headOffice, plant);
        return purchaseOrderRepository.save(po);
    }

    public PurchaseOrder getByPoNumber(String poNumber) {
        PurchaseOrder po = purchaseOrderRepository.findByPoNumber(poNumber)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found for PO Number: " + poNumber));

        return po;
    }

    public List<PurchaseOrderDTO> getAllPo() {
        List<PurchaseOrder> poList = purchaseOrderRepository.findAll();

        return poList.stream()
                .map(PurchaseOrderMapper::toDTO)
                .collect(Collectors.toList());
    }

}
