package com.example.OrderService.Controller;

import com.example.OrderService.Repository.OrderRepository;
import com.example.OrderService.Request.OrderRequest;
import com.example.OrderService.Response.OrderResponse;
import com.example.OrderService.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")

@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @Autowired
    RestTemplate restTemplate;
@PostMapping("/placeorder")
    public ResponseEntity<Long>placeOrder(@RequestBody OrderRequest orderRequest){
        long orderId= orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse>getOrderDetails(@PathVariable long orderId){
    OrderResponse orderResponse=orderService.getOrderDetails(orderId);
    return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

}
