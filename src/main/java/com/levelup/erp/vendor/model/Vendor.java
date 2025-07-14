package com.levelup.erp.vendor.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_code", nullable = false, unique = true)
    private String vendorCode;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String city;
    private String region;
    private String country;

    @Column(nullable = false)
    private String pincode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_type_code")
    private BusinessType businessType;

    @Column(name = "govt_id")
    private String govtId;

    @Column(name = "gst_number")
    private String gstNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "payment_currency")
    private String paymentCurrency;

    @Column(name = "purchasing_group")
    private String purchasingGroup;

    @Column(name = "onboarding_date")
    private LocalDate onboardingDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;

    @Column(name = "isd_code")
    private String isdCode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Optional: one-to-many vendor to bank accounts
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorBankAccount> bankAccounts;

    // Optional: many-to-many vendor to plant
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorPlant> vendorPlants;
}
