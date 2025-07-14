package com.levelup.erp.vendor.controller;

import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.repository.PlantRepository;
import com.levelup.erp.vendor.service.BusinessTypeService;
import com.levelup.erp.vendor.service.PlantService;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VendorController {

    private final VendorService vendorService;
    private final BusinessTypeService businessTypeService;
    private final PlantService plantService;

    public VendorController(VendorService vendorService,
                            BusinessTypeService businessTypeService,
                            PlantService plantService) {
        this.vendorService = vendorService;
        this.businessTypeService = businessTypeService;
        this.plantService = plantService;
    }

    @GetMapping("/vendors")
    public String showVendorDashboard(Model model) {
        List<VendorDTO> vendorDTOs = vendorService.getAllVendorDTOs();
        model.addAttribute("vendors", vendorDTOs);
        return "vendor/vendors";
    }

    @GetMapping("/vendors/add")
    public String showAddVendorForm(Model model) {
        model.addAttribute("vendorDTO", new VendorDTO());
        model.addAttribute("businessTypes", businessTypeService.getAllBusinessTypes());
        model.addAttribute("plants", plantService.getAllPlants());
        return "vendor/addVendor";
    }


}