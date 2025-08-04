package com.levelup.erp.po.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubMaterialDTO {

    private Integer srNo;
    private String itemCode;
    private String description;
    private Integer quantity;
    private String unitOfMeasurement;
    private Float unitPrice;

}