package com.tanuja.Order_Service.Controller;

import com.tanuja.Order_Service.Service.OrderService;
import com.tanuja.Order_Service.dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        if (orderRequest.getOrderLineItemsDtoList() == null) {
            return "OrderLineItemsDtoList cannot be null";
        }
        orderService.placeOrder(orderRequest);
        return "Order Placed";
    }

}
