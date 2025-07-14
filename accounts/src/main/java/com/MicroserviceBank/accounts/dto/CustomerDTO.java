package com.MicroserviceBank.accounts.dto;

import com.MicroserviceBank.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account info."
)
public class CustomerDTO {

    @Schema(
            description = "Name of the customer", example = "Air Jordan"
    )
    @NotEmpty(message = "Name cannot be be empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Customer`s email", example = "jair@realg.com"
    )
    @NotEmpty(message = "Email cannot be be empty")
    @Email(message = "Invalid email")
    private String email;

    @Schema(
            description = "Customer`s mobile number", example = "38045678910"
    )
    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String mobileNumber;

    private AccountsDTO accountsDTO;

}
