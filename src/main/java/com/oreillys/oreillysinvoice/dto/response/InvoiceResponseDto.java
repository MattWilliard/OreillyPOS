package com.oreillys.oreillysinvoice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class InvoiceResponseDto {
    private Map<Integer, String> invoices;
}
