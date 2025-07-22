package com.levelup.erp.vendor.repository;

import com.levelup.erp.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    List<Vendor> findByNameContainingIgnoreCaseOrVendorCodeContainingIgnoreCase(String name, String code);
    Optional<Vendor> findByVendorCode(String vendorCode);
    List<Vendor> findByVendorCodeContainingIgnoreCaseOrNameContainingIgnoreCase(String code, String name);
    void deleteByVendorCode(String vendorCode);
    Long countByPincode(String pincode);


    @Query("SELECT v.vendorCode FROM Vendor v")
    List<String> findAllVendorCodes();


}
