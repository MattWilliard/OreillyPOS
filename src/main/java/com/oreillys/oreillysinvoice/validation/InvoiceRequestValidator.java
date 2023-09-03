package com.oreillys.oreillysinvoice.validation;

import com.oreillys.oreillysinvoice.dto.request.InvoiceRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InvoiceRequestValidator implements ConstraintValidator<InvoiceRequestValidation, InvoiceRequestDto> {
    @Override
    public boolean isValid(InvoiceRequestDto invoiceRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        try{
            if(invoiceRequestDto.getCustomerId() <= 0){
                return false;
            } else if (invoiceRequestDto.getCustomerId() >= 1000){
                constraintValidatorContext.buildConstraintViolationWithTemplate("Customer ID format is invalid.").addConstraintViolation();
                return false;
            } else {
                return true;
            }
        }catch(Exception e){
            return false;
        }
    }
}
