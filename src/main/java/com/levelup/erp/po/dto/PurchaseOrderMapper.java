package com.levelup.erp.po.dto;

import com.levelup.erp.organisation.models.HeadOffice;
import com.levelup.erp.organisation.models.Plant;
import com.levelup.erp.po.models.PurchaseOrder;
import com.levelup.erp.po.models.PurchaseOrderStatus;
import com.levelup.erp.po.models.SubMaterial;

import java.util.List;
import java.util.stream.Collectors;

public  class PurchaseOrderMapper {

    public static PurchaseOrder toEntity(PurchaseOrderDTO dto, HeadOffice headOffice, Plant plant) {
        if (dto == null) return null;

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setVendorCode(dto.getVendorCode());
        purchaseOrder.setBillTo(headOffice);  // already fetched
        purchaseOrder.setShipTo(plant);       // already fetched
        purchaseOrder.setPoNumber(dto.getPoNumber());
        purchaseOrder.setPoDate(dto.getPoDate());
        purchaseOrder.setPoCurrency(dto.getPoCurrency());
        purchaseOrder.setModeOfDilivery(dto.getModeOfDilivery());
        purchaseOrder.setTermOfDilivery(dto.getTermOfDilivery());
        purchaseOrder.setPortOfDischarge(dto.getPortOfDischarge());
        purchaseOrder.setPlaceOfFinalDestination(dto.getPlaceOfFinalDestination());
        purchaseOrder.setPaymentTerm(dto.getPaymentTerm());
        purchaseOrder.setTotalAmount(dto.getTotalAmount());
        purchaseOrder.setTotalAmountInWords(dto.getTotalAmountInWords());
        purchaseOrder.setAdditionalNotes(dto.getAdditionalNotes());

        // Map SubMaterials
        if (dto.getLifOfSubMaterial() != null) {
            List<SubMaterial> subMaterials = dto.getLifOfSubMaterial()
                    .stream()
                    .map(smDto -> {
                        SubMaterial sm = new SubMaterial();
                        sm.setSrNo(smDto.getSrNo());
                        sm.setItemCode(smDto.getItemCode());
                        sm.setDescription(smDto.getDescription());
                        sm.setQuantity(smDto.getQuantity());
                        sm.setUnitOfMeasurement(smDto.getUnitOfMeasurement());
                        sm.setUnitPrice(smDto.getUnitPrice());
                        sm.setPurchaseOrder(purchaseOrder); // set back-reference
                        return sm;
                    })
                    .collect(Collectors.toList());
            purchaseOrder.setLifOfSubMaterial(subMaterials);
        }

        purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.OPEN); // Default to OPEN
        return purchaseOrder;
    }

    public static PurchaseOrderDTO toDTO(PurchaseOrder entity) {
        if (entity == null) return null;

        PurchaseOrderDTO dto = new PurchaseOrderDTO();
        dto.setVendorCode(entity.getVendorCode());
        dto.setBillToId(entity.getBillTo().getId());
        dto.setShipToPlantId(entity.getShipTo().getId());
        dto.setPoNumber(entity.getPoNumber());
        dto.setPoDate(entity.getPoDate());
        dto.setPoCurrency(entity.getPoCurrency());
        dto.setModeOfDilivery(entity.getModeOfDilivery());
        dto.setTermOfDilivery(entity.getTermOfDilivery());
        dto.setPortOfDischarge(entity.getPortOfDischarge());
        dto.setPlaceOfFinalDestination(entity.getPlaceOfFinalDestination());
        dto.setPaymentTerm(entity.getPaymentTerm());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setTotalAmountInWords(entity.getTotalAmountInWords());
        dto.setAdditionalNotes(entity.getAdditionalNotes());

        if (entity.getLifOfSubMaterial() != null) {
            List<SubMaterialDTO> subMaterialDTOs = entity.getLifOfSubMaterial()
                    .stream()
                    .map(sm -> {
                        SubMaterialDTO smDto = new SubMaterialDTO();
                        smDto.setSrNo(sm.getSrNo());
                        smDto.setItemCode(sm.getItemCode());
                        smDto.setDescription(sm.getDescription());
                        smDto.setQuantity(sm.getQuantity());
                        smDto.setUnitOfMeasurement(sm.getUnitOfMeasurement());
                        smDto.setUnitPrice(sm.getUnitPrice());
                        return smDto;
                    })
                    .collect(Collectors.toList());
            dto.setLifOfSubMaterial(subMaterialDTOs);
        }

        return dto;
    }
}
