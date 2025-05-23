package com.projects.EcommerceApp.service;

import com.projects.EcommerceApp.dto.OrderDTO;
import com.projects.EcommerceApp.dto.OrderRequestDTO;
import com.projects.EcommerceApp.model.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {

    OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrderByUser(Long userId);

    OrderRequest convertDtoToEntity(@Valid OrderRequestDTO orderRequestDTO);
}
