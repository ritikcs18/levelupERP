package com.levelup.erp.vendor.controller;
import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.service.BusinessTypeService;
import com.levelup.erp.vendor.service.PlantService;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showVendors(@RequestParam(value = "mode", required = false, defaultValue = "view") String mode, Model model) {
        List<VendorDTO> vendors = vendorService.getAllVendorDTOs();
        model.addAttribute("vendors", vendors);

        if ("add".equalsIgnoreCase(mode)) {
            model.addAttribute("vendorDTO", new VendorDTO());
            model.addAttribute("plants", plantService.getAllPlants());
            model.addAttribute("businessTypes", businessTypeService.getAllBusinessTypes());
            model.addAttribute("showAddForm", true);
        } else {
            model.addAttribute("showAddForm", false);
        }
        return "vendor/vendors";
    }


    @PostMapping("/vendors/add")
    public String addVendor(@ModelAttribute("vendorDTO") VendorDTO vendorDTO) {
        vendorService.saveVendorFromDTO(vendorDTO);
        return "redirect:/vendors";
    }

    @GetMapping("/vendors/search")
    public String searchVendors(@RequestParam("keyword") String keyword, Model model) {
        List<VendorDTO> vendors = vendorService.searchVendors(keyword.trim());
        model.addAttribute("vendors", vendors);
        model.addAttribute("keyword", keyword); // So you can show it in the input box again
        model.addAttribute("showAddForm", false); // Always show table on search

        return "vendor/vendors";
    }




}