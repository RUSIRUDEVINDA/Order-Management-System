package com.order.service.controller;

import com.order.service.dto.OrderDTO;
import com.order.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/orders")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable int id, @RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id){
        orderService.deleteOrder(id);
    }
}
