package com.PaymentService.paymentService.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private long oderId;
    private long amount;
    private String referenceNumber;
    private String paymentMode;
}
