package com.order.service.controller;

import com.order.service.common.OrderResponse;
import com.order.service.dto.OrderDTO;
import com.order.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public List<OrderDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrder/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/addOrder")
    public OrderResponse addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @PutMapping("/updateOrder")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.deleteOrder(orderDTO);
    }
}