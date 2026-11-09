package com.popcorn.nanoservice.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequest {
    private List<String> itemIds;
    private String customerEmail;
    private String customerPhoneNumber;
    private String deliveryAddress;
}
