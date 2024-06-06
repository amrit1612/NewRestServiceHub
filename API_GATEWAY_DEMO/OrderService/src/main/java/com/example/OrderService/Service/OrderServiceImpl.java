package com.example.OrderService.Service;

import com.example.OrderService.Entity.Order;
import com.example.OrderService.Exception.OrderServiceCustomException;
import com.example.OrderService.Repository.OrderRepository;
import com.example.OrderService.Request.OrderRequest;
import com.example.OrderService.Request.PaymentRequest;
import com.example.OrderService.Response.OrderResponse;
import com.example.OrderService.Response.PaymentResponse;
import com.example.OrderService.Response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        Order order=Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("Created")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order=orderRepository.save(order);

        PaymentRequest paymentRequest=PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus=null;
        try{
            orderStatus="PLACED";
        }catch (Exception e){
            orderStatus="PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {

        Order order=orderRepository.findById(orderId)
                .orElseThrow(()->new OrderServiceCustomException("Order not found for the order id:"+orderId,"Not_Found",404));
        log.info("OrderServiceImpl | getOrderDetails | Invoking Product service to fetch the product for id: {}", order.getProductId());
        ProductResponse productResponse=restTemplate.getForObject(
                "http://localhost:8084/product/" + order.getProductId(),ProductResponse.class
        );

        log.info("OrderServiceImpl | getOrderDetails | Getting payment information form the payment Service");


        PaymentResponse paymentResponse=restTemplate.getForObject("http://localhost:8086/payment/" + order.getId(), PaymentResponse.class
                );
        OrderResponse.ProductDetails productDetails
                =OrderResponse.ProductDetails
                .builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();

        OrderResponse.PaymentDetails paymentDetails1
                =OrderResponse.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentmode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse orderResponse=OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails1)
                .build();
        return orderResponse;
    }
}
