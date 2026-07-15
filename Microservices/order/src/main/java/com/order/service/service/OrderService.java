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
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO getOrderById(Integer id) {
        Order order = orderRepo.findById(id).orElse(null);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO addOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }

    public String deleteOrder(OrderDTO orderDTO) {
        orderRepo.delete(modelMapper.map(orderDTO, Order.class));
        return "ORDER DELETED";
    }
}