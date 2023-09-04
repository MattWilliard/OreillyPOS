package com.oreillys.invoice.pos.dto.request;

import com.oreillys.invoice.pos.validation.InvoiceRequestValidation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@InvoiceRequestValidation
public class InvoiceRequestDto {
    private int customerId;
}
