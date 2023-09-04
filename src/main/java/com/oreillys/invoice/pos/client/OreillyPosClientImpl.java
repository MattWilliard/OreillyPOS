package com.oreillys.invoice.pos.client;


import com.oreillys.invoice.pos.database.repository.InvoiceRepository;
import com.oreillys.invoice.pos.database.model.Invoice;
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
        return invoiceRepository.getInvocesByCustomerId(customerId);
    }
}
