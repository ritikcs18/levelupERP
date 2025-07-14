package com.levelup.erp.vendor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VendorDTO {
    private Long id;
    private String name;
    private String vendorCode;
    private String businessTypeName;
    private String country;
    private String city;
    private String address;
    private String pinCode;
    private String email;
    private String contactNumber;
    private String gstNumber;
    private boolean active;
    private LocalDateTime onboardingDate;
    private List<String> vendorPlants;

}