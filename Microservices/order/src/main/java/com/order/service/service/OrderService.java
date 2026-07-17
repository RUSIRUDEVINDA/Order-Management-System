package com.order.service.service;

import com.inventory.service.dto.InventoryDTO;
import com.order.service.common.ErrorOrderResponse;
import com.order.service.common.OrderResponse;
import com.order.service.common.SuccessOrderResponse;
import com.order.service.dto.OrderDTO;
import com.order.service.model.Order;
import com.order.service.repo.OrderRepo;
import com.product.service.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;


    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient, WebClient productWebClient, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {
        }.getType());
    }

    public OrderDTO getOrderById(Integer id) {
        Order order = orderRepo.findById(id).orElse(null);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderResponse addOrder(OrderDTO orderDTO) {
        try {
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri("/getItem/{itemId}", orderDTO.getItemID())
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            if (inventoryResponse == null) {
                return new ErrorOrderResponse("Inventory item not found");
            }

            ProductDTO productResponse = productWebClient.get()
                    .uri("/getProduct/{productId}", inventoryResponse.getProductID())
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            if (productResponse == null) {
                return new ErrorOrderResponse("Product not found");
            }

            if (inventoryResponse.getQuantity() <= 0) {
                return new ErrorOrderResponse("Item not available");
            }

            if (productResponse.getForSale() != 1) {
                return new ErrorOrderResponse("This product is not for sale");
            }

            orderRepo.save(modelMapper.map(orderDTO, Order.class));
            return new SuccessOrderResponse(orderDTO);

        } catch (WebClientResponseException e) {
            if(e.getStatusCode().is5xxServerError()) {
                return new ErrorOrderResponse("item not found");
            }
        }
        return null;
    }
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }
    public String deleteOrderById(Integer id) {
        orderRepo.deleteById(id);
        return "ORDER DELETED";
    }
}