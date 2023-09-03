package com.oreillys.oreillysinvoice.client;

import com.oreillys.oreillysinvoice.database.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface OreillyPosClient {
    public List<Invoice> getInvoiceByCustomerId(Long customerId);
}
