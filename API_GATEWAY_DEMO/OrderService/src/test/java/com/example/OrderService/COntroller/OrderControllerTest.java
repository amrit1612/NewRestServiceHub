package com.example.OrderService.COntroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import com.example.OrderService.Controller.OrderController;
// import com.example.OrderService.Entity.Order;
import com.example.OrderService.Request.OrderRequest;
import com.example.OrderService.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OrderControllerTest {
    
    
    OrderService orderService = Mockito.mock(OrderService.class);
    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    
    @Test
    public void placeOrderTest() {  
    OrderController orderController = Mockito.mock(OrderController.class);
    OrderRequest orderRequest = new OrderRequest();
    long expectedOrderId = 1L;
    
    when(orderService.placeOrder(orderRequest)).thenReturn(expectedOrderId);

        // Call the method
        ResponseEntity<Long> result = orderController.placeOrder(orderRequest);

        // Assert the result
        assertEquals(new ResponseEntity<>(1L, HttpStatus.OK), result);
   }

}



