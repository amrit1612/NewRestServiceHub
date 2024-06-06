package com.example.OrderService.Service;

import com.example.OrderService.Request.OrderRequest;
import com.example.OrderService.Response.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
    OrderResponse getOrderDetails(long orderId);
}
