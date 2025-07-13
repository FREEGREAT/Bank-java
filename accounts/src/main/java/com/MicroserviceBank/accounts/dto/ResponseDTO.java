package com.MicroserviceBank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private Integer statusCode;
    private String statusMessage;

}
