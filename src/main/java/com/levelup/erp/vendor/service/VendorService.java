package com.levelup.erp.vendor.service;

import com.levelup.erp.vendor.dto.VendorDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    //VendorDTO addVendor(VendorDTO dto);

    void saveVendorFromDTO(VendorDTO vendorDTO);

    List<VendorDTO> searchVendorsByNameOrCode(String keyword);

    //void updateVendorFromDTO(VendorDTO dto);

    Optional<VendorDTO> getVendorDTOByCode(String vendorCode);

    void updateVendor(VendorDTO vendorDTO);

    void deleteByVendorCode(String code);

    void importVendorsFromCsv(MultipartFile file) throws Exception;
}
