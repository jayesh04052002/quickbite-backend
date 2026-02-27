package com.foodapp.foodapp.controller;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodapp.model.Order;
import com.foodapp.foodapp.model.OrderItem;
import com.foodapp.foodapp.repository.OrderRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class InvoiceController {

    @Autowired private OrderRepository orderRepo;

    @GetMapping("/orders/{id}/invoice")
    public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long id) throws Exception {

        Order order = orderRepo.findByIdWithItems(id).orElseThrow();

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                float y = 750;

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
                cs.newLineAtOffset(50, y);
                cs.showText("FoodApp - Invoice");
                cs.endText();

                y -= 30;
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, y);
                cs.showText("Order ID: " + order.getId());
                cs.endText();

                y -= 18;
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, y);
                cs.showText("User ID: " + order.getUserId() + " | Restaurant ID: " + order.getRestaurantId());
                cs.endText();

                y -= 25;
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
                cs.newLineAtOffset(50, y);
                cs.showText("Items:");
                cs.endText();

                y -= 18;

                if (order.getItems() != null) {
                    for (OrderItem it : order.getItems()) {
                        String line = it.getName() + " x" + it.getQty() + " = ₹ " + (it.getPrice() * it.getQty());
                        cs.beginText();
                        cs.setFont(PDType1Font.HELVETICA, 12);
                        cs.newLineAtOffset(50, y);
                        cs.showText(line);
                        cs.endText();
                        y -= 16;
                    }
                }

                y -= 10;
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
                cs.newLineAtOffset(50, y);
                cs.showText("Total: ₹ " + order.getTotal());
                cs.endText();
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            doc.save(out);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.attachment().filename("invoice-order-" + id + ".pdf").build()
            );

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
        }
    }
}