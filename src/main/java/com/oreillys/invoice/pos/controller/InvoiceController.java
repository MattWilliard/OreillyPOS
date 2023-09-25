package com.oreillys.invoice.pos.controller;

import com.oreillys.invoice.pos.dto.request.InvoiceRequestDto;
import com.oreillys.invoice.pos.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    /** Using a POST method with a request body since the customer id is PII and HTTPS will automatically encrypt the request via TLS */
    @PostMapping("/invoice")
    ResponseEntity<Object> getInvoices(@Valid @RequestBody InvoiceRequestDto invoiceRequest) {
        return invoiceService.getInvoiceMap(invoiceRequest.getCustomerId());
    }
}
