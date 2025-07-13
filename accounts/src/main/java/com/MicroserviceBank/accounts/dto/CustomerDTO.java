package com.MicroserviceBank.accounts.dto;

import com.MicroserviceBank.accounts.entity.Accounts;
import lombok.Data;

@Data
public class CustomerDTO {
    private String name;

    private String email;

    private String mobileNumber;

    private AccountsDTO accountsDTO;

}
