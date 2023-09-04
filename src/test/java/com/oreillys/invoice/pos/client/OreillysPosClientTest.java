package com.oreillys.invoice.pos.client;

import com.oreillys.invoice.pos.database.model.Invoice;
import com.oreillys.invoice.pos.database.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OreillysPosClientTest {

    @Spy
    @InjectMocks
    OreillyPosClientImpl oreillyPosClient;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    void testGetInvoiceByCustomerId(){
        when(invoiceRepository.getInvocesByCustomerId(any(Long.class))).thenReturn(getMockInvoiceList());
        List<Invoice> actualInvoiceList = oreillyPosClient.getInvoiceByCustomerId(2L);
        assertNotNull(actualInvoiceList);
    }

    private List<Invoice> getMockInvoiceList(){
        List<Invoice> mockInvoiceList = new ArrayList<>();
        Invoice mockInv1 = new Invoice();
        mockInv1.setCustomerId(2L);
        mockInv1.setInvoiceId(55);
        mockInv1.setInvoiceJson("{\"time\": \"19:53\",\"tenderDetails\": {\"amount\": 23.43,\"type\": \"cash\"},\"storeNumber\": \"999\"}");
        mockInvoiceList.add(mockInv1);
        Invoice mockInv2 = new Invoice();
        mockInv2.setCustomerId(2L);
        mockInv2.setInvoiceId(56);
        mockInv2.setInvoiceJson("{\"time\": \"8:49\",\"tenderDetails\": {\"amount\": 100.12,\"type\": \"credit\"},\"storeNumber\": \"999\"}");
        mockInvoiceList.add(mockInv2);
        return mockInvoiceList;
    }

}
