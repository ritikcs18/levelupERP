package com.levelup.erp.vendor.repository;

import com.levelup.erp.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    List<Vendor> findByNameContainingIgnoreCaseOrVendorCodeContainingIgnoreCase(String name, String code);
}
