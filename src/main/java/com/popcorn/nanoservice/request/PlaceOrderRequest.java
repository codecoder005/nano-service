package com.popcorn.nanoservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "PlaceOrderRequest",
        description = "Request payload for placing a new order",
        example = """
        {
          "itemIds": ["ITM-1001", "ITM-2002"],
          "customerEmail": "jane.doe@example.com",
          "customerPhoneNumber": "+919876543210",
          "deliveryAddress": "221B Baker Street, Bengaluru, Karnataka 560001, India"
        }"""
)
public class PlaceOrderRequest {
    @Schema(
            description = "IDs of the items to order. Duplicate IDs are not allowed; one entry per distinct item.",
            example = "[\"ITM-1001\", \"ITM-2002\"]",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 1
    )
    @NotEmpty
    @Size(max = 50)
    private List<@NotBlank String> itemIds;

    @Schema(
            description = "Customer's email address, used for the order confirmation and receipt",
            example = "jane.doe@example.com",
            format = "email",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 254
    )
    @NotBlank
    @Email
    @Size(max = 254)
    private String customerEmail;

    @Schema(
            description = "Customer's phone number in E.164 format (country code, no spaces or dashes), used for delivery updates",
            example = "+919876543210",
            requiredMode = Schema.RequiredMode.REQUIRED,
            pattern = "^\\+[1-9]\\d{7,14}$"
    )
    @NotBlank
    @Pattern(regexp = "^\\+[1-9]\\d{7,14}$", message = "must be in E.164 format, e.g. +919876543210")
    private String customerPhoneNumber;

    @Schema(
            description = "Full delivery address including street, city, state, postal code and country",
            example = "221B Baker Street, Bengaluru, Karnataka 560001, India",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 10,
            maxLength = 500
    )
    @NotBlank
    @Size(min = 10, max = 500)
    private String deliveryAddress;
}
