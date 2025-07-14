package com.MicroserviceBank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information."
)
public class ResponseDTO {
    @Schema(
            description = "Status code in the response", example = "200"
    )
    private Integer statusCode;
    @Schema(
            description = "Status message in the response", example = "Oh my GOD! Good job, oleG!"
    )
    private String statusMessage;

}
