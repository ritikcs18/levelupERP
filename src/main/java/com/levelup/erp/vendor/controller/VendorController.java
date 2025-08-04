package com.levelup.erp.vendor.controller;
import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.service.BusinessTypeService;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class VendorController {

    private final VendorService vendorService;
    private final BusinessTypeService businessTypeService;

    public VendorController(VendorService vendorService,
                            BusinessTypeService businessTypeService
                            ) {
        this.vendorService = vendorService;
        this.businessTypeService = businessTypeService;
    }

//
//    @GetMapping("/vendors/view")
//    public String viewVendors(Model model) {
//        List<VendorDTO> vendors = vendorService.getAllVendors(); // Replace with your actual method
//        model.addAttribute("vendors", vendors);
//        return "vendor/view-vendor";
//    }


    @GetMapping("/vendors/view")
    public String viewVendors(@RequestParam(required = false) String keyword,
                              Model model) {
        List<VendorDTO> vendors;

        if (keyword != null && !keyword.isBlank()) {
            vendors = vendorService.searchVendorsByNameOrCode(keyword); // Implement this method
        } else {
            vendors = vendorService.getAllVendors();
        }

        model.addAttribute("vendors", vendors);
        model.addAttribute("keyword", keyword); // keep keyword in input box
        return "vendor/view-vendor";
    }




    // Show form (GET)
    @GetMapping("/vendors/add")
    public String showAddVendorForm(Model model) {
        model.addAttribute("vendorDTO", new VendorDTO());
        model.addAttribute("businessTypes", businessTypeService.getAllBusinessTypes());
        return "vendor/addvendor";  // Thymeleaf template
    }

//    // Submit form (POST)
//    @PostMapping("/vendors/add")
//    public String addVendor(@ModelAttribute("vendorDTO") VendorDTO vendorDTO) {
//        vendorService.saveVendorFromDTO(vendorDTO);
//        return "redirect:/vendors/view";  // Redirect to view page after saving
//    }

    @PostMapping("/vendors/add")
    public String addVendor(@ModelAttribute("vendorDTO") VendorDTO vendorDTO,
                            RedirectAttributes redirectAttributes) {
        vendorService.saveVendorFromDTO(vendorDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Vendor added successfully!");
        return "redirect:/vendors/add";
    }


    //Search
    @GetMapping("/vendors/search")
    public String searchVendors(@RequestParam("keyword") String keyword, Model model) {
        List<VendorDTO> vendors = vendorService.searchVendorsByNameOrCode(keyword.trim());
        model.addAttribute("vendors", vendors);
        model.addAttribute("keyword", keyword); // So you can show it in the input box again
        model.addAttribute("showAddForm", false); // Always show table on search

        return "vendor/vendors";
    }

    @GetMapping("/vendors/details")
    public String showVendorDetails(@RequestParam("id") String vendorCode, Model model) {
        Optional<VendorDTO> vendor = vendorService.getVendorDTOByCode(vendorCode);
        model.addAttribute("vendor", vendor);
        model.addAttribute("showDetails", true);
        model.addAttribute("showAddForm", false); // optional - to hide others
        return "vendor/vendors";
    }



    @PostMapping("/vendors/update")
    public String updateVendor(@ModelAttribute("vendor") VendorDTO vendorDTO) {
        vendorService.updateVendor(vendorDTO); // Write logic to map DTO to entity and save

        return "redirect:/vendor/view-vendor"; // or success page
    }

    @GetMapping("/vendors/update")
    public String showUpdateVendorForm(Model model) {
        model.addAttribute("vendorDTO", new VendorDTO()); // blank DTO
        model.addAttribute("businessTypes", businessTypeService.getAllBusinessTypes());
        return "vendor/updatevendor"; // Thymeleaf template
    }


    @PostMapping(value = "/vendors/deleteVendor")
    public String deleteVendorForm(@RequestParam("code") String code, Model model) {
        try {
            vendorService.deleteByVendorCode(code);
            model.addAttribute("deleteSuccess", true);
        } catch (Exception e) {
            model.addAttribute("deleteError", "Vendor not found or could not be deleted.");
        }
        return "vendor/deletevendor";
    }

    @GetMapping("/vendors/delete")
    public String showDeleteVendorForm(Model model) {
        model.addAttribute("vendor", new VendorDTO()); // empty form
        return "vendor/deletevendor"; // points to your delete.html template
    }


    @PostMapping("/vendors/bulkUpload")
    public String uploadVendors(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("uploadError", "Please select a file to upload.");
            return "vendor/upload"; // adjust to your view name
        }

        try {
            vendorService.importVendorsFromCsv(file);
            model.addAttribute("uploadSuccess", "Vendors uploaded successfully.");
        } catch (Exception e) {
            model.addAttribute("uploadError", "Error uploading vendors: " + e.getMessage());
        }

        return "vendor/bulkupload"; // adjust to your view name
    }


    @GetMapping("/vendors/upload")
    public String showBulkUploadPage() {
        return "vendor/bulkupload"; // Name of your Thymeleaf HTML file under templates/vendor/
    }






}