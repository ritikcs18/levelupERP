package com.levelup.erp.po.controller;


import com.levelup.erp.po.dto.PurchaseOrderDTO;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseOrderController {

    VendorService vendorService;

    public PurchaseOrderController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/po/add")
    public String showCreatePOForm(Model model) {
        model.addAttribute("purchaseOrderDTO", new PurchaseOrderDTO()); // Bind form fields
        model.addAttribute("vendors", vendorService.getAllVendors());   // Populate dropdown
        return "po/purchaseorder"; // Thymeleaf template
    }

    @GetMapping("/po/view")
    public String showAllPurchaseOrder(Model model) {
        return  "po/viewPo";
    }






}
