package com.levelup.erp.vendor.controller.apis;

import com.levelup.erp.vendor.dto.VendorDTO;
import com.levelup.erp.vendor.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/api/vendors")
@RestController
public class VendorRestController {

    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }



//    @PostMapping("/update")
//    public String updateVendor(@ModelAttribute("vendorDTO") VendorDTO dto) {
//        vendorService.updateVendorFromDTO(dto);
//        return "redirect:/vendors/view";
//    }


    @GetMapping("/{vendorCode}")
    public ResponseEntity<VendorDTO> getVendorDTOJson(@PathVariable String vendorCode) {
        return vendorService.getVendorDTOByCode(vendorCode)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
