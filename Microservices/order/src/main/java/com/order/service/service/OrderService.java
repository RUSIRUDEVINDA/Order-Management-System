package com.order.service.service;

import com.order.service.dto.OrderDTO;
import com.order.service.model.Order;
import com.order.service.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {
        }.getType());
    }

    public OrderDTO getOrderById(Integer id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order savedOrder = orderRepo.save(order);
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        Order existingOrder = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setAmount(orderDTO.getAmount());
        Order updatedOrder = orderRepo.save(existingOrder);
        return modelMapper.map(updatedOrder, OrderDTO.class);
    }

    public void deleteOrder(Integer orderId) {
        orderRepo.deleteById(orderId);
    }

}
