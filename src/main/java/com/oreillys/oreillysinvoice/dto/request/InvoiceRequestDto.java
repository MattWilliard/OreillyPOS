package com.oreillys.oreillysinvoice.dto.request;

import com.oreillys.oreillysinvoice.validation.InvoiceRequestValidation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@InvoiceRequestValidation
public class InvoiceRequestDto {
    private int customerId;
}
