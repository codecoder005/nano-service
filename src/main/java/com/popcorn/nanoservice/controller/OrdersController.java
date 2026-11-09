package com.popcorn.nanoservice.controller;

import com.google.gson.Gson;
import com.popcorn.nanoservice.request.PlaceOrderRequest;
import com.popcorn.nanoservice.response.PlaceOrderResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(
        name = "Orders API",
        description = "Endpoints for creating, retrieving, updating and cancelling orders",
        externalDocs = @ExternalDocumentation(
                description = "Order lifecycle documentation",
                url = "https://example.com/docs/orders"
        )
)
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {
    private final Gson jsonHelper;

    @Operation(
            summary = "Place an order",
            description = """
        Creates a new order for the given items and returns its ID and initial status. \
        The customer's email, phone number and delivery address are validated before the order is accepted."""
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Order placed successfully",
                    content = @Content(schema = @Schema(implementation = PlaceOrderResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content),
            @ApiResponse(responseCode = "409", description = "One or more items are out of stock", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaceOrderResponse> placeOrder(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order details", required = true)
            @Valid @RequestBody PlaceOrderRequest orderRequest
    ) {
        log.info("Received a place order request {}", jsonHelper.toJson(orderRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(PlaceOrderResponse.builder().paymentStatus("SUCCESS").orderId(UUID.randomUUID().toString()).orderStatus("PLACED").build());
    }
}
