package com.oreillys.invoice.pos.client;

import com.oreillys.invoice.pos.database.model.Invoice;

import java.util.List;

public interface OreillyPosClient {
    public List<Invoice> getInvoiceByCustomerId(Long customerId);
}
