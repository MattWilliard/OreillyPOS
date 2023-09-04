package com.oreillys.invoice.pos.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TenderTypeTest {

    @Test
    void should(){
        assertEquals("cash", TenderType.CASH.getLabel());
        assertEquals("credit", TenderType.CREDIT.getLabel());
    }

}
