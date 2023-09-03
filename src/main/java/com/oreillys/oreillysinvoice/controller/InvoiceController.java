package com.oreillys.oreillysinvoice.controller;

import com.oreillys.oreillysinvoice.dto.request.InvoiceRequestDto;
import com.oreillys.oreillysinvoice.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/invoice")
    ResponseEntity<Object> newEmployee(@Valid @RequestBody InvoiceRequestDto invoiceRequest) {
        return invoiceService.getInvoiceMap(invoiceRequest.getCustomerId());
    }
}
