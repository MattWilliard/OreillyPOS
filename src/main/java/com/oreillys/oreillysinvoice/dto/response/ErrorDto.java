package com.oreillys.oreillysinvoice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private String status;
    private String message;
    private List<String> errors;
}
