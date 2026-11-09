package com.popcorn.nanoservice.controller;

import com.google.gson.Gson;
import com.popcorn.nanoservice.request.PlaceOrderRequest;
import com.popcorn.nanoservice.response.PlaceOrderResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest orderRequest) {
        log.info("Received a place order request {}", jsonHelper.toJson(orderRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(PlaceOrderResponse.builder().paymentStatus("SUCCESS").orderId("987").orderStatus("PLACED").build());
    }
}
