package com.levelup.erp.po.service;

import com.levelup.erp.po.dto.PurchaseOrderDTO;
import com.levelup.erp.po.models.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    public byte[] generatePurchaseOrderPdf(PurchaseOrder purchaseOrder) {
        Context context = new Context();
        context.setVariable("po", purchaseOrder);

        // Render Thymeleaf template into HTML
        String htmlContent = templateEngine.process("po/poPdf", context);

        // Convert HTML to PDF
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        try (OutputStream os = pdfStream) {
            com.openhtmltopdf.pdfboxout.PdfRendererBuilder builder = new com.openhtmltopdf.pdfboxout.PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, "");
            builder.toStream(os);
            builder.run();
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF", e);
        }

        return pdfStream.toByteArray();
    }
}