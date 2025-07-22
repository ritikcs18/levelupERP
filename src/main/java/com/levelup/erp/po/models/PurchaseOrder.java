package com.levelup.erp.po.models;

import com.levelup.erp.vendor.model.Vendor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PurchaseOrder extends BaseEntityPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String poNumber; // system-generated

    private LocalDate poDate;

    private String currency;

    private String modeOfDelivery;

    private String termsOfDelivery;

    private String portOfDischarge;

    private String finalDestination;

    private String paymentTerm;

    @Enumerated(EnumType.STRING)
    private POStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private String internalRemarks;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrderItem> items = new ArrayList<>();
}

