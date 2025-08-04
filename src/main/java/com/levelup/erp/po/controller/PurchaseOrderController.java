package com.levelup.erp.po.controller;
import com.levelup.erp.organisation.service.OrganisationService;
import com.levelup.erp.po.dto.PurchaseOrderDTO;
import com.levelup.erp.po.service.PurchaseOrderService;
import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PurchaseOrderController {

    VendorService vendorService;
    OrganisationService organisationService;
    PurchaseOrderService purchaseOrderService;


    public PurchaseOrderController(VendorService vendorService,
                                   OrganisationService organisationService,
                                   PurchaseOrderService purchaseOrderService
    ) {
        this.vendorService = vendorService;
        this.organisationService = organisationService;
        this.purchaseOrderService  = purchaseOrderService;
    }

    @GetMapping("/po/view")
    public String viewPo(@RequestParam(required = false) String keyword,
                              Model model) {
        model.addAttribute("purchaseOrderDTO", purchaseOrderService.getAllPo());
        return "po/viewpo";
    }


    @GetMapping("/po/add")
    public String showAddPoForm(Model model) {
        model.addAttribute("puchaseOrderDTO", new PurchaseOrderDTO());
        model.addAttribute("vendors", vendorService.getAllVendorCodes()); // Replace with actual method
        model.addAttribute("plants", organisationService.plantList());
        model.addAttribute("headOffices", organisationService.headOfficeList());

        return "po/addpo";
    }



    @PostMapping("/po/save")
    public String savePurchaseOrder(@ModelAttribute("puchaseOrderDTO") PurchaseOrderDTO purchaseOrderDTO) {

        // Save PO
        purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);

        // Redirect to add page with download param
        return "redirect:/po/add?download=" + purchaseOrderDTO.getPoNumber();
    }



}
