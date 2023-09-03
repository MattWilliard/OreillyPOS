package com.oreillys.oreillysinvoice.dto.response;

import lombok.Data;

@Data
public class InvoiceData {
    private String time;
    private String storeNumber;
    private TenderDetails tenderDetails;
}
