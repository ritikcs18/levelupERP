package com.levelup.erp.vendor.service.impl;
import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.model.*;
import com.levelup.erp.vendor.repository.BusinessTypeRepository;
import com.levelup.erp.vendor.repository.PlantRepository;
import com.levelup.erp.vendor.repository.VendorRepository;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final BusinessTypeRepository businessTypeRepository;
    private  final PlantRepository plantRepository;


    public VendorServiceImpl(VendorRepository vendorRepository,
                             BusinessTypeRepository businessTypeRepository,
                             PlantRepository plantRepository) {
        this.vendorRepository = vendorRepository;
        this.businessTypeRepository = businessTypeRepository;
        this.plantRepository = plantRepository;
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
            dto.setOnboardingDate(v.getOnboardingDate());

            List<String> plantNames = v.getVendorPlants() != null
                    ? v.getVendorPlants().stream()
                    .map(vp -> vp.getPlant().getName())
                    .collect(Collectors.toList())
                    : new ArrayList<>();
            dto.setVendorPlants(plantNames);

            return dto;
        }).collect(Collectors.toList());
    }

    public void saveVendorFromDTO(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setVendorCode(dto.getVendorCode());
        vendor.setEmail(dto.getEmail());
        vendor.setContactNumber(dto.getContactNumber());
        vendor.setCountry(dto.getCountry());
        vendor.setCity(dto.getCity());
        vendor.setAddress(dto.getAddress());
        vendor.setPincode(dto.getPinCode());
        vendor.setGstNumber(dto.getGstNumber());
        vendor.setOnboardingDate(LocalDate.from(dto.getOnboardingDate()));
        vendor.setActive(dto.isActive());

        // Set Business Type
        BusinessType businessType = businessTypeRepository.findByName(dto.getBusinessTypeName())
                .orElseThrow(() -> new RuntimeException("Invalid business type: " + dto.getBusinessTypeName()));
        vendor.setBusinessType(businessType);

        // Set Vendor Bank Account
        if (dto.getAccountNumber() != null && dto.getIfscCode() != null) {
            VendorBankAccount account = new VendorBankAccount();
            account.setAccountNumber(dto.getAccountNumber());
            account.setIfscCode(dto.getIfscCode());
            account.setVendor(vendor);

            List<VendorBankAccount> accounts = new ArrayList<>();
            accounts.add(account);
            vendor.setBankAccounts(accounts); //
        }

        // Set Plants
        if (dto.getVendorPlants() != null && !dto.getVendorPlants().isEmpty()) {
            List<VendorPlant> vendorPlants = dto.getVendorPlants().stream()
                    .map(plantName -> {
                        Plant plant = plantRepository.findByName(plantName)
                                .orElseThrow(() -> new RuntimeException("Plant not found: " + plantName));
                        VendorPlant vp = new VendorPlant();
                        vp.setVendor(vendor);
                        vp.setPlant(plant);
                        return vp;
                    }).collect(Collectors.toList());
            vendor.setVendorPlants(vendorPlants);
        }

        vendorRepository.save(vendor);
    }


    public List<VendorDTO> searchVendors(String keyword) {
        List<Vendor> vendors;

        if (keyword == null || keyword.trim().isEmpty()) {
            vendors = vendorRepository.findAll();
        } else {
            vendors = vendorRepository.findByNameContainingIgnoreCaseOrVendorCodeContainingIgnoreCase(keyword, keyword);
        }

        return vendors.stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    private VendorDTO mapToDTO(Vendor vendor) {
        VendorDTO dto = new VendorDTO();
        dto.setId(vendor.getId());
        dto.setName(vendor.getName());
        dto.setVendorCode(vendor.getVendorCode());
        dto.setBusinessTypeName(vendor.getBusinessType().getName());
        dto.setCountry(vendor.getCountry());
        dto.setCity(vendor.getCity());
        dto.setAddress(vendor.getAddress());
        dto.setPinCode(vendor.getPincode());
        dto.setEmail(vendor.getEmail());
        dto.setContactNumber(vendor.getContactNumber());
        dto.setGstNumber(vendor.getGstNumber());
        dto.setActive(vendor.isActive());
        dto.setOnboardingDate(vendor.getOnboardingDate());

        if (vendor.getVendorPlants() != null) {
            List<String> plantNames = vendor.getVendorPlants().stream()
                    .map(vp -> vp.getPlant().getName())
                    .collect(Collectors.toList());
            dto.setVendorPlants(plantNames);
        }

        return dto;
    }





}