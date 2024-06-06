package com.example.OrderService.COntroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    //when long orderId= orderService.placeOrder(orderRequest);
    //return new ResponseEntity<>(orderId, HttpStatus.OK);
    
    
    when(orderService.placeOrder(orderRequest)).thenReturn(expectedOrderId);
    when (orderController.placeOrder(orderRequest)).thenReturn(new ResponseEntity<>(expectedOrderId, HttpStatus.OK));
        // Call the method
        ResponseEntity<Long> result = orderController.placeOrder(orderRequest);
    
        // Assert the result
        assertNotNull(result);
       
       assertEquals(HttpStatus.OK, result.getStatusCode());
       assertEquals(expectedOrderId, result.getBody().longValue());
    }
   }





