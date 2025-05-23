package com.projects.EcommerceApp.controller;

import com.projects.EcommerceApp.dto.OrderDTO;
import com.projects.EcommerceApp.model.OrderRequest;
import com.projects.EcommerceApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public OrderDTO placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(userId,orderRequest.getProductQuantities(),orderRequest.getTotalAmount());
    }

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderByUserId/{UserId}")
    public List<OrderDTO> getOrderByUserId(@PathVariable Long UserId){
        return orderService.getOrderByUser(UserId);
    }

}
