package com.levelup.erp.vendor.repository;

import com.levelup.erp.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
