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
    // Method to load the payment details by orderId
    @GetMapping("/load/{orderId}")
    public ResponseEntity<PaymentResponse>loadPaymentDetailsByOrderId(@PathVariable long orderId){
        return new ResponseEntity<>(
                paymentService.loadPaymentDetailsByOrderId(orderId),HttpStatus.OK
        );
    }

    // Method to update the payment details by orderId
    @PutMapping("/update/{orderId}")
    public ResponseEntity<PaymentResponse>updatePaymentDetailsByOrderId(@PathVariable long orderId,@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(
                paymentService.updatePaymentDetailsByOrderId(orderId,paymentRequest),HttpStatus.OK
        );
    }
// Method to delete the payment details by orderId
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String>deletePaymentDetailsByOrderId(@PathVariable long orderId){
        return new ResponseEntity<>(
                paymentService.deletePaymentDetailsByOrderId(orderId),HttpStatus.OK
        );
    }    

// Method to delete all the payment details
    @DeleteMapping("/delete/all")
    public ResponseEntity<String>deleteAllPaymentDetails(){
        return new ResponseEntity<>(
                paymentService.deleteAllPaymentDetails(),HttpStatus.OK
        );
    }
    //Telemetry API
    @GetMapping("/telemetry")
    public ResponseEntity<String>telemetry(){
        return new ResponseEntity<>(
                paymentService.telemetry(),HttpStatus.OK
        );
    }
    //Health API
    @GetMapping("/health")
    public ResponseEntity<String>health(){
        return new ResponseEntity<>(
                paymentService.health(),HttpStatus.OK
        );
    }
    //Info API
    @GetMapping("/info")
    public ResponseEntity<String>info(){
        return new ResponseEntity<>(
                paymentService.info(),HttpStatus.OK
        );
    }
}
