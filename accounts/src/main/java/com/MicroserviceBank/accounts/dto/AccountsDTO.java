package com.MicroserviceBank.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold account info."
)
public class AccountsDTO {


    @Schema(
            description = "Account number", example = "1234567891"
    )
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type", example = "Stake"
    )
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @Schema(
            description = "Address", example = "Some fake address idk 228b"
    )
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
