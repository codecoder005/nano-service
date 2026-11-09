package com.popcorn.nanoservice.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderResponse {
    private String orderId;
    private String orderStatus;
    private String paymentStatus;
}
