package com.oreillys.oreillysinvoice.client;


import com.oreillys.oreillysinvoice.database.model.Invoice;
import com.oreillys.oreillysinvoice.database.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OreillyPosClientImpl implements OreillyPosClient {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getInvoiceByCustomerId(Long customerId) {
        List<Invoice> invoiceList = invoiceRepository.getInvocesByCustomerId(customerId);
        return invoiceList;
    }
}
