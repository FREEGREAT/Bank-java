package com.MicroserviceBank.accounts.controller;

import com.MicroserviceBank.accounts.constants.AccountsConstants;
import com.MicroserviceBank.accounts.dto.CustomerDTO;
import com.MicroserviceBank.accounts.dto.ErrorResponseDTO;
import com.MicroserviceBank.accounts.dto.ResponseDTO;
import com.MicroserviceBank.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API for Accounts in MicroserviceBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, GET and DELETE account detail"
)
@RestController
@RequestMapping(path = "/api/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountService iAccountService;

    @Operation(
            summary = "Create account endpoint.",
            description = "Endpoint ro create new Customer and Account."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status code: Created"
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        iAccountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "FETCH account detail endpoint.",
            description = "Endpoint to FETCH Customer and Account details."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status code: OK"
    )
    @GetMapping
    public ResponseEntity<CustomerDTO> fetchAccountDetail(@RequestParam
                                                              @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
                                                              String mobileNumber) {
        CustomerDTO customerDTO = iAccountService.fetchAccountDetail(mobileNumber);
        return  ResponseEntity.status(HttpStatus.OK)
                .body(customerDTO);

    }

    @Operation(
            summary = "UPDATE account detail endpoint.",
            description = "Endpoint to UPDATE Customer && Account detail."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status code: OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status code: INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })

    @PutMapping
    public ResponseEntity<ResponseDTO> updateAccountDetail(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = iAccountService.updateAccount(customerDTO);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "DELETE account endpoint.",
            description = "Endpoint to DELETE Customer && Account."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status code: OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status code: INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )

    })
    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteAccountDetail(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
                                                               String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }
}
