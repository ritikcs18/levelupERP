package com.levelup.erp.po.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {

    private String vendorCode;
    private Long billToId;
    private Long shipToPlantId;
    private String poNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate poDate;
    private String poCurrency;
    private String modeOfDilivery;
    private String termOfDilivery;
    private String portOfDischarge;
    private String placeOfFinalDestination;
    private String paymentTerm;
    private Float totalAmount;
    private String totalAmountInWords;
    private String additionalNotes;
    private List<SubMaterialDTO> lifOfSubMaterial = new ArrayList<>();
}



