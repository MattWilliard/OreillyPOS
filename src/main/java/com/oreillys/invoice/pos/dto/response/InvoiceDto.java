package com.oreillys.invoice.pos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Long customerId;
    private int invoiceId;
    private InvoiceData invoiceData;
}
