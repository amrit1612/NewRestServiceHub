package com.PaymentService.paymentService.Service;

import com.PaymentService.paymentService.Entity.TransactionDetails;
import com.PaymentService.paymentService.Exception.PaymentServiceCustomException;
import com.PaymentService.paymentService.Repository.TransationDatailRepository;
import com.PaymentService.paymentService.Request.PaymentRequest;
import com.PaymentService.paymentService.Response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final TransationDatailRepository transationDatailRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        TransactionDetails transactionDetails= TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode())
                .paymentStatus("Success")
                .orderId(paymentRequest.getOderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
        transactionDetails=transationDatailRepository.save(transactionDetails);

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        TransactionDetails transactionDetails=transationDatailRepository.findByOrderId(orderId)
                .orElseThrow(()->new PaymentServiceCustomException("TransactionalDetails with given id not found",
                "TRANSACTION_NOT_FOUND"));

        PaymentResponse paymentResponse= PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .paymentMode(transactionDetails.getPaymentMode())
                .build();
        return paymentResponse;
    }

    @Override
    public List<TransactionDetails> getAll() {
        return transationDatailRepository.findAll();
    }
}
