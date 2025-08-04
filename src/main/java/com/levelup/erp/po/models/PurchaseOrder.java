package com.levelup.erp.po.models;

import com.levelup.erp.organisation.models.HeadOffice;
import com.levelup.erp.organisation.models.Plant;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bill_to_id")
    private HeadOffice billTo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ship_to_id")
    private Plant shipTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate poDate;

    @Column(unique = true, nullable = false)
    private String poNumber;

    private String poCurrency;
    private String modeOfDilivery;
    private String termOfDilivery;
    private String portOfDischarge;
    private String placeOfFinalDestination;
    private String paymentTerm;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SubMaterial> lifOfSubMaterial;

    private Float totalAmount;
    private String totalAmountInWords;

    @Lob
    private String additionalNotes;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;

}
