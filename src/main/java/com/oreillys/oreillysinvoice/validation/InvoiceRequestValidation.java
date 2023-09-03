package com.oreillys.oreillysinvoice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Constraint(validatedBy = {InvoiceRequestValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InvoiceRequestValidation {
    public String message() default "Validation failed for the input parameter.";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
