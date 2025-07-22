package com.levelup.erp.material.dto;
import com.levelup.erp.material.models.FinishedMaterial;
import com.levelup.erp.material.models.MRPType;

public class Mapper {

    public static FinishedMaterialDTO fromEntity(FinishedMaterial entity) {
        FinishedMaterialDTO dto = new FinishedMaterialDTO();

        dto.setPlant(entity.getPlant());
        dto.setHeaderMaterial(entity.getHeaderMaterial());
        dto.setSubMaterial(entity.getSubMaterial());
        dto.setMaterialDescription(entity.getMaterialDescription());
        dto.setMaterialTypeDescription(entity.getMaterialTypeDescription());
        dto.setMaterialGroup(entity.getMaterialGroup());
        dto.setBaseUnitOfMeasure(entity.getBaseUnitOfMeasure());
        dto.setMrpType(entity.getMrpType());
        dto.setCurrency(entity.getCurrency());
        dto.setNlp(entity.getNlp());
        dto.setArtworkNumber(entity.getArtworkNumber());
        dto.setArtworkRevisionNo(entity.getArtworkRevisionNo());
        dto.setTechnicalSpecNo(entity.getTechnicalSpecNo());
        dto.setTechnicalSpecRevisionNo(entity.getTechnicalSpecRevisionNo());
        dto.setPurchasingGroup(entity.getPurchasingGroup());
        dto.setValuationClass(entity.getValuationClass());
        dto.setGtinNumber(entity.getGtinNumber());

        return dto;
    }


    public static FinishedMaterial toEntity(FinishedMaterialDTO dto) {
        FinishedMaterial entity = new FinishedMaterial();

        entity.setPlant(dto.getPlant());
        entity.setHeaderMaterial(dto.getHeaderMaterial());
        entity.setSubMaterial(dto.getSubMaterial());
        entity.setMaterialDescription(dto.getMaterialDescription());
        entity.setMaterialTypeDescription(dto.getMaterialTypeDescription());
        entity.setMaterialGroup(dto.getMaterialGroup());
        entity.setBaseUnitOfMeasure(dto.getBaseUnitOfMeasure());
        entity.setMrpType(dto.getMrpType());
        entity.setCurrency(dto.getCurrency());
        entity.setNlp(dto.getNlp());
        entity.setArtworkNumber(dto.getArtworkNumber());
        entity.setArtworkRevisionNo(dto.getArtworkRevisionNo());
        entity.setTechnicalSpecNo(dto.getTechnicalSpecNo());
        entity.setTechnicalSpecRevisionNo(dto.getTechnicalSpecRevisionNo());
        entity.setPurchasingGroup(dto.getPurchasingGroup());
        entity.setValuationClass(dto.getValuationClass());
        entity.setGtinNumber(dto.getGtinNumber());

        return entity;
    }





}
