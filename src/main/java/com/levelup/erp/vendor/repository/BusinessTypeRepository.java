package com.levelup.erp.vendor.repository;

import com.levelup.erp.vendor.model.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, String> {

    Optional<BusinessType> findByName(String name);
    Optional<BusinessType> findByCode(String code);

}
