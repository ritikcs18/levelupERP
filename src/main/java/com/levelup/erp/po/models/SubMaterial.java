package com.levelup.erp.po.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer srNo;
    private String itemCode;
    private Integer quantity;
    private String description;
    private String unitOfMeasurement;
    private Float unitPrice;

    @ManyToOne
    private PurchaseOrder purchaseOrder;
}