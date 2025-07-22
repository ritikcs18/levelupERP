package com.levelup.erp.vendor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDTO {
    private Long id;
    private String vendorCode;
    private String name;
    private String address;
    private String city;
    private String region;
    private String country;
    private String pincode;
    private String govtId;
    private String gstNumber;
    private String email;
    private boolean isActive;
    private String paymentCurrency;
    private String purchasingGroup;
    private LocalDate onboardingDate;
    private LocalDate exitDate;
    private String isdCode;
    private String contactNumber;
    private String businessTypeCode;
    private String accountNumber;
    private String ifscCode;
}
