package com.popcorn.nanoservice.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "PlaceOrderResponse",
        description = "Response returned after an order has been successfully placed",
        example = """
        {
          "orderId": "ORD-20261109-000123",
          "orderStatus": "CONFIRMED",
          "paymentStatus": "PENDING"
        }"""
)
public class PlaceOrderResponse {
    @Schema(
            description = "Unique identifier of the newly created order. Use it to track or cancel the order.",
            example = "ORD-20261109-000123",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String orderId;

    @Schema(
            description = "Current lifecycle state of the order",
            example = "CONFIRMED",
            allowableValues = {"CONFIRMED", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED"},
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String orderStatus;

    @Schema(
            description = "Current state of the payment for this order",
            example = "PENDING",
            allowableValues = {"PENDING", "PAID", "FAILED", "REFUNDED"},
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String paymentStatus;
}
