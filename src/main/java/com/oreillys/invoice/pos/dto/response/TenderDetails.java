package com.oreillys.invoice.pos.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TenderDetails {
    private BigDecimal amount;
    private String type;
}
