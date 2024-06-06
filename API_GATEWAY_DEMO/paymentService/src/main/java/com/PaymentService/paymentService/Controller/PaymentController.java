package com.PaymentService.paymentService.Controller;

import com.PaymentService.paymentService.Entity.TransactionDetails;
import com.PaymentService.paymentService.Request.PaymentRequest;
import com.PaymentService.paymentService.Response.PaymentResponse;
import com.PaymentService.paymentService.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long>doPayment(@RequestBody PaymentRequest paymentRequest){

        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }
    @GetMapping("{orderId}")
    public ResponseEntity<PaymentResponse>getPaymentDetailsByOrderId(@PathVariable long orderId){
       return new ResponseEntity<>(
               paymentService.getPaymentDetailsByOrderId(orderId),HttpStatus.OK
       );
    }
    @GetMapping("/all")
    public List<TransactionDetails> getAll(){
        return paymentService.getAll();

    }

}
