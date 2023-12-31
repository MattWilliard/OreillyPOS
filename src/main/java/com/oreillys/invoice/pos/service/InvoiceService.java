package com.oreillys.invoice.pos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreillys.invoice.pos.client.OreillyPosClientImpl;
import com.oreillys.invoice.pos.dto.response.ErrorDto;
import com.oreillys.invoice.pos.dto.response.InvoiceData;
import com.oreillys.invoice.pos.dto.response.InvoiceDto;
import com.oreillys.invoice.pos.dto.response.InvoiceResponseDto;
import com.oreillys.invoice.pos.database.model.Invoice;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final OreillyPosClientImpl oreillyPosClient;

    public ResponseEntity<Object> getInvoiceMap(int customerId) {
        List<InvoiceDto> invoiceDtoList = null;
        InvoiceResponseDto invoiceResponseDto = null;
        List<Invoice> dbInvoiceList = oreillyPosClient.getInvoiceByCustomerId(Long.valueOf(customerId));

        if(dbInvoiceList != null && dbInvoiceList.size() > 0){
            try {
                invoiceDtoList = convertDbInvoiceToInvoiceDto(dbInvoiceList);
            } catch(JsonProcessingException jpe){
                ErrorDto error = createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), null, Arrays.asList(jpe.getMessage()));
                return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            ErrorDto error = createErrorResponse(HttpStatus.NOT_FOUND.toString(), "No invoice(s) found for provided customer id.", null);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        if(invoiceDtoList != null && invoiceDtoList.size() > 0){
            invoiceResponseDto = getInvoiceResponseDto(invoiceDtoList);
        }

        return new ResponseEntity<>(invoiceResponseDto, HttpStatus.OK);
    }

    private List<InvoiceDto> convertDbInvoiceToInvoiceDto(List<Invoice> dbInvoiceList) throws JsonProcessingException {
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        for(Invoice inv: dbInvoiceList){
            InvoiceDto dto = new InvoiceDto();
            dto.setCustomerId(inv.getCustomerId() != null ? inv.getCustomerId() : null);
            dto.setInvoiceId(inv.getInvoiceId() > 0 ? inv.getInvoiceId() : null);
            if(inv.getInvoiceJson() != null) {
                InvoiceData invoiceData = new InvoiceData();
                ObjectMapper mapper = new ObjectMapper();
                dto.setInvoiceData(mapper.readValue(inv.getInvoiceJson(), InvoiceData.class));
            }
            invoiceDtoList.add(dto);
        }
        return invoiceDtoList;
    }

    private InvoiceResponseDto getInvoiceResponseDto(List<InvoiceDto> invoiceDtoList){
        InvoiceResponseDto responseDto = new InvoiceResponseDto();
        Map<Integer, String> invoiceIds = new HashMap<>();
        for(InvoiceDto inv: invoiceDtoList){
            if(inv.getInvoiceId() > 0 && inv.getInvoiceData() != null &&
                    inv.getInvoiceData().getTenderDetails() != null &&
                    inv.getInvoiceData().getTenderDetails().getType() != null){
                invoiceIds.put(inv.getInvoiceId(),inv.getInvoiceData().getTenderDetails().getType());
            }
        }
        responseDto.setInvoices(invoiceIds);
        return responseDto;
    }

    private ErrorDto createErrorResponse(String status, String message, List<String> errors){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(status);
        errorDto.setMessage(message);
        errorDto.setErrors(errors);
        return errorDto;
    }


}
