package com.oreillys.invoice.pos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreillys.invoice.pos.dto.request.InvoiceRequestDto;
import com.oreillys.invoice.pos.filter.AuthenticationFilter;
import com.oreillys.invoice.pos.service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class InvoiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InvoiceService invoiceService;

    @MockBean
    AuthenticationFilter authenticationFilter;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(authenticationFilter, "authenticate", false);
    }

    @Test
    void getInvoicesReturnsSuccess() throws Exception {
        InvoiceRequestDto mockRequest = new InvoiceRequestDto();
        mockRequest.setCustomerId(2);

        mockMvc.perform(post("/api/v1/invoice")
                .content(new ObjectMapper().writeValueAsString(mockRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getInvoicesReturnsBadRequest() throws Exception {
        InvoiceRequestDto mockRequest = new InvoiceRequestDto();
        mockRequest.setCustomerId(0);

        mockMvc.perform(post("/api/v1/invoice")
                        .content(new ObjectMapper().writeValueAsString(mockRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getInvoicesReturnsBadRequestForCustomerIdTooLarge() throws Exception {
        InvoiceRequestDto mockRequest = new InvoiceRequestDto();
        mockRequest.setCustomerId(1234);

        mockMvc.perform(post("/api/v1/invoice")
                        .content(new ObjectMapper().writeValueAsString(mockRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
