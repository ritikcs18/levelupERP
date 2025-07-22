package com.levelup.erp.material.dto;

import com.levelup.erp.material.models.MRPType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinishedMaterialDTO {

    private Integer plant;
    private String headerMaterial;
    private String subMaterial;
    private String materialDescription;
    private String materialTypeDescription;
    private String materialGroup;
    private String baseUnitOfMeasure;
    private MRPType mrpType;
    private String currency;
    private String nlp;
    private String artworkNumber;
    private String artworkRevisionNo;
    private String technicalSpecNo;
    private String technicalSpecRevisionNo;
    private String purchasingGroup;
    private String valuationClass;
    private String gtinNumber;

}
