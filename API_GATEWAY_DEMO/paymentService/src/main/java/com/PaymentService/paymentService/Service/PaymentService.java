package com.PaymentService.paymentService.Service;

import com.PaymentService.paymentService.Entity.TransactionDetails;
import com.PaymentService.paymentService.Request.PaymentRequest;
import com.PaymentService.paymentService.Response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);

    List<TransactionDetails> getAll();
}
