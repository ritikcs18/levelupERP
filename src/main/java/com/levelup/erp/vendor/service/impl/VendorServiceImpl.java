package com.levelup.erp.vendor.service.impl;

import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.model.Vendor;
import com.levelup.erp.vendor.repository.VendorRepository;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public List<VendorDTO> getAllVendorDTOs() {
        return vendorRepository.findAll().stream().map(v -> {
            VendorDTO dto = new VendorDTO();
            dto.setId(v.getId());
            dto.setName(v.getName());
            dto.setVendorCode(v.getVendorCode());
            dto.setBusinessTypeName(v.getBusinessType() != null ? v.getBusinessType().getName() : "");
            dto.setCountry(v.getCountry());
            dto.setCity(v.getCity());
            dto.setEmail(v.getEmail());
            dto.setAddress(v.getAddress());
            dto.setPinCode(v.getPincode());
            dto.setContactNumber(v.getContactNumber());
            dto.setGstNumber(v.getGstNumber());
            dto.setActive(v.isActive());
            dto.setOnboardingDate(v.getOnboardingDate().atStartOfDay());

            List<String> plantNames = v.getVendorPlants() != null
                    ? v.getVendorPlants().stream()
                    .map(vp -> vp.getPlant().getName())
                    .collect(Collectors.toList())
                    : new ArrayList<>();
            dto.setVendorPlants(plantNames);

            return dto;
        }).collect(Collectors.toList());
    }
}