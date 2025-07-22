package com.levelup.erp.po.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_id")
    private PurchaseOrder purchaseOrder;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sub_material_id")
//    //private SubMaterial subMaterial;

    //private Double quantity;

    //private String uom; // Unit of Measure (e.g., KG, BOX, PCS)

    //private BigDecimal unitPrice;

    // Total cost = quantity * unitPrice — this can be calculated dynamically in the DTO or frontend

    // Getters, Setters
}
