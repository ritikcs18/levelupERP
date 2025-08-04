package com.levelup.erp.po.controller;

import com.levelup.erp.po.dto.PurchaseOrderDTO;
import com.levelup.erp.po.models.PurchaseOrder;
import com.levelup.erp.po.service.PdfService;
import com.levelup.erp.po.service.PurchaseOrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PdfController {

    PurchaseOrderService purchaseOrderService;
    PdfService pdfService;


    public PdfController(PurchaseOrderService purchaseOrderService, PdfService pdfService) {
        this.purchaseOrderService = purchaseOrderService;
        this.pdfService = pdfService;
    }

    @GetMapping("/po/{id}/pdf")
    public ResponseEntity<byte[]> downloadPurchaseOrderPdf(@PathVariable String id) {
        PurchaseOrder po = purchaseOrderService.getByPoNumber(id);
        byte[] pdfBytes = pdfService.generatePurchaseOrderPdf(po);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=purchase-order-" + po.getPoNumber() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
