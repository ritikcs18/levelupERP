package com.levelup.erp.vendor.service;

import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.model.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    List<VendorDTO> getAllVendorDTOs();
    void saveVendorFromDTO(VendorDTO vendorDTO);

    List<VendorDTO> searchVendors(String keyword);
}
