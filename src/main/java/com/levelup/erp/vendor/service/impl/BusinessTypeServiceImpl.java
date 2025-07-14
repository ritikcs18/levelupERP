package com.levelup.erp.vendor.service.impl;

import com.levelup.erp.vendor.model.BusinessType;
import com.levelup.erp.vendor.repository.BusinessTypeRepository;
import com.levelup.erp.vendor.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

    private final BusinessTypeRepository businessTypeRepository;

    @Autowired
    public BusinessTypeServiceImpl(BusinessTypeRepository businessTypeRepository) {
        this.businessTypeRepository = businessTypeRepository;
    }

    @Override
    public List<BusinessType> getAllBusinessTypes() {
        return businessTypeRepository.findAll();
    }
}