package com.levelup.erp.vendor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BusinessType {
    @Id
    private String code;  // e.g., WSS, TR, RT

    private String name;
    private String description;

    @OneToMany(mappedBy = "businessType")
    private List<Vendor> vendors = new ArrayList<>();
}
