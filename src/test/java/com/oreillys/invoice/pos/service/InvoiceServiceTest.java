package com.oreillys.invoice.pos.service;

import com.oreillys.invoice.pos.client.OreillyPosClientImpl;
import com.oreillys.invoice.pos.database.model.Invoice;
import com.oreillys.invoice.pos.dto.response.ErrorDto;
import com.oreillys.invoice.pos.dto.response.InvoiceResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InvoiceServiceTest {

    @Spy
    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    OreillyPosClientImpl oreillyPosClient;

    @Test
    void testInvoiceServiceReturnsResponseEntity(){
        when(oreillyPosClient.getInvoiceByCustomerId(any(Long.class))).thenReturn(getMockInvoiceList());
        ResponseEntity<Object> actualResponse = invoiceService.getInvoiceMap(2);
        assertNotNull(actualResponse);
        assertTrue(actualResponse.getBody() instanceof InvoiceResponseDto);
        assertEquals(2, ((InvoiceResponseDto) actualResponse.getBody()).getInvoices().size());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    void testInvoiceServiceReturnsResponseEntityAfterBadJson(){
        when(oreillyPosClient.getInvoiceByCustomerId(any(Long.class))).thenReturn(getMockInvoiceListWithBadJson());
        ResponseEntity<Object> actualResponse = invoiceService.getInvoiceMap(1);
        assertNotNull(actualResponse);
        assertTrue(actualResponse.getBody() instanceof ErrorDto);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
    }

    @Test
    void testInvoiceServiceReturnsResponseEntityWithCustomerIdNotFound(){
        when(oreillyPosClient.getInvoiceByCustomerId(any(Long.class))).thenReturn(new ArrayList<Invoice>());
        ResponseEntity<Object> actualResponse = invoiceService.getInvoiceMap(1);
        assertNotNull(actualResponse);
        assertTrue(actualResponse.getBody() instanceof ErrorDto);
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
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

    private List<Invoice> getMockInvoiceListWithBadJson(){
        List<Invoice> mockInvoiceList = new ArrayList<>();
        Invoice mockInv1 = new Invoice();
        mockInv1.setCustomerId(1L);
        mockInv1.setInvoiceId(55);
        mockInv1.setInvoiceJson("{\"time\": \"19:53\"\"tenderDetails\": {\"amount\": 23.43,\"type\": \"cash\"}\"storeNumber\": \"999\"}");
        mockInvoiceList.add(mockInv1);
        return mockInvoiceList;
    }

}
