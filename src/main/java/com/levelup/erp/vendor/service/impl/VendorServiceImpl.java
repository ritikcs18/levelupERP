package com.levelup.erp.vendor.service.impl;

import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.dto.VendorMapper;
import com.levelup.erp.vendor.model.Vendor;
import com.levelup.erp.vendor.repository.BusinessTypeRepository;
import com.levelup.erp.vendor.repository.VendorBankAccountRepository;
import com.levelup.erp.vendor.repository.VendorRepository;
import com.levelup.erp.vendor.service.VendorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service

public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorBankAccountRepository bankAccountRepository;
    private final BusinessTypeRepository businessTypeRepository;

    public VendorServiceImpl(VendorRepository vendorRepository,
                             VendorBankAccountRepository bankAccountRepository,
                             BusinessTypeRepository businessTypeRepository) {

        this.vendorRepository = vendorRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.businessTypeRepository = businessTypeRepository;
    }


    @Override
    public List<VendorDTO> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        return vendors.stream()
                .map(VendorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllVendorCodes() {
        return vendorRepository.findAll()
                .stream()
                .map(Vendor::getVendorCode)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void saveVendorFromDTO(VendorDTO vendorDTO) {

        vendorDTO.setVendorCode(generateVendorCode(vendorDTO.getPincode()));
        Vendor vendor = VendorMapper.toEntity(vendorDTO);
        // Set Business Type if present
        if (vendorDTO.getBusinessTypeCode() != null) {
            businessTypeRepository.findByCode(vendorDTO.getBusinessTypeCode())
                    .ifPresent(vendor::setBusinessType);
        }
        vendorRepository.save(vendor);
    }

    @Override
    public List<VendorDTO> searchVendorsByNameOrCode(String keyword) {
        List<Vendor> vendors = vendorRepository.findByVendorCodeContainingIgnoreCaseOrNameContainingIgnoreCase(keyword, keyword);
        return vendors.stream()
                .map(VendorMapper::toDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public void updateVendor(VendorDTO dto) {
        Vendor existing = vendorRepository.findByVendorCode(dto.getVendorCode())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        Vendor updated = VendorMapper.toEntity(dto);
        updated.setId(existing.getId());
        vendorRepository.save(updated);
    }


    public Optional<VendorDTO> getVendorDTOByCode(String vendorCode) {
        Optional<Vendor> vendor = vendorRepository.findByVendorCode(vendorCode);
        return vendorRepository.findByVendorCode(vendorCode)
                .map(VendorMapper::toDTO);
    }

    @Transactional
    public void deleteByVendorCode(String vendorCode) {
        Vendor vendor = vendorRepository.findByVendorCode(vendorCode)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendorRepository.deleteByVendorCode(vendorCode);
    }


    @Transactional
    public void saveAll(List<Vendor> vendors) throws Exception {

        try {
            vendorRepository.saveAll(vendors);

        }catch (Exception ex){
            throw new Exception("Failed to parse CSV file: " + ex.getMessage());
        }

    }



    public void importVendorsFromCsv(MultipartFile file) throws Exception {
        List<Vendor> vendors = new ArrayList<>();

        Map<String, Integer> existingMaxSeqMap = new HashMap<>();
        Map<String, Integer> localSeqMap = new HashMap<>();

        // Preload existing vendor codes
        List<String> existingCodes = vendorRepository.findAllVendorCodes(); // You write this method
        for (String code : existingCodes) {
            String[] parts = code.split("-");
            if (parts.length == 3) {
                String pin = parts[1];
                int seq = Integer.parseInt(parts[2]);
                existingMaxSeqMap.put(pin, Math.max(existingMaxSeqMap.getOrDefault(pin, 0), seq));
            }
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length < 19) {
                    throw new Exception("Invalid CSV format. Row must have 19 columns.");
                }

                VendorDTO dto = new VendorDTO();
                dto.setName(fields[0].trim());
                dto.setAddress(fields[1].trim());
                dto.setCity(fields[2].trim());
                dto.setRegion(fields[3].trim());
                dto.setCountry(fields[4].trim());
                dto.setPincode(fields[5].trim());
                dto.setGovtId(fields[6].trim());
                dto.setGstNumber(fields[7].trim());
                dto.setEmail(fields[8].trim());
                dto.setActive(Boolean.parseBoolean(fields[9].trim()));
                dto.setPaymentCurrency(fields[10].trim());
                dto.setPurchasingGroup(fields[11].trim());
                dto.setOnboardingDate(LocalDate.parse(fields[12].trim()));
                dto.setExitDate(fields[13].isBlank() ? null : LocalDate.parse(fields[13].trim()));
                dto.setIsdCode(fields[14].trim());
                dto.setContactNumber(fields[15].trim());
                dto.setBusinessTypeCode(fields[16].trim());
                dto.setAccountNumber(fields[17].trim());
                dto.setIfscCode(fields[18].trim());
                //dto.setVendorCode(generateVendorCode(dto.getPincode()));

                String vendorCode = generateVendorCodeForBulkUpload(dto.getPincode(), existingMaxSeqMap, localSeqMap);
                dto.setVendorCode(vendorCode);

                // Convert to entity
                Vendor vendor = VendorMapper.toEntity(dto);

                // Set BusinessType
//                BusinessType businessType = businessTypeRepository.findByCode(dto.getBusinessTypeCode())
//                        .orElseThrow(() -> new RuntimeException("Invalid BusinessType code: " + dto.getBusinessTypeCode()));
//                vendor.setBusinessType(businessType);

                vendors.add(vendor);
            }

            saveAll(vendors);

        } catch (Exception e) {
            throw new Exception("Failed to parse CSV file: " + e.getMessage(), e);
        }
    }


    private String generateVendorCode(String pincode) {
        // Fetch count of existing vendors with same pincode and country
        long count = vendorRepository.countByPincode(pincode);
        // Increment for the new vendor
        long nextNumber = count + 1;
        String countryCode = "001";

        // Format number as 3-digit with leading zeros
        String formattedNumber = String.format("%03d", nextNumber);

        // Build vendor code
        return String.format("VN-%s-%s-%s", pincode, countryCode.toUpperCase(), formattedNumber);
    }

    public String generateVendorCodeForBulkUpload(String pincode,
                                                  Map<String, Integer> existingMaxSeqMap,
                                                  Map<String, Integer> localSeqMap) {
        int baseSeq = existingMaxSeqMap.getOrDefault(pincode, 0);
        int nextSeq = localSeqMap.getOrDefault(pincode, baseSeq) + 1;

        localSeqMap.put(pincode, nextSeq);
        String countryCode = "001";
        String formattedNumber = String.format("%03d", nextSeq);

        return String.format("VN-%s-%s-%s", pincode, countryCode.toUpperCase(), formattedNumber);
    }





}