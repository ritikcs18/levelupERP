package com.levelup.erp.vendor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VendorDTO {
    private Long id;
    private String name;        //madatory
    private String vendorCode;   //madatory
    private String businessTypeName;  //madatory
    private String country;//madatory
    private String city;//madatory
    private String address;
    private String pinCode;//madatory
    private String email;
    private String contactNumber;//madatory
    private String gstNumber;//madatory
    private boolean active; //madatory
    //private LocalDateTime onboardingDate; //madatory
    private LocalDate onboardingDate;
    private List<String> vendorPlants;
    private String accountNumber;
    private String ifscCode;

}