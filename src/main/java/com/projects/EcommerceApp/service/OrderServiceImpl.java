package com.projects.EcommerceApp.service;
import com.projects.EcommerceApp.dto.OrderDTO;
import com.projects.EcommerceApp.dto.OrderItemsDTO;
import com.projects.EcommerceApp.model.OrderItems;
import com.projects.EcommerceApp.model.Orders;
import com.projects.EcommerceApp.model.Product;
import com.projects.EcommerceApp.model.User;
import com.projects.EcommerceApp.repository.OrderRepository;
import com.projects.EcommerceApp.repository.ProductRepository;
import com.projects.EcommerceApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Override
    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Orders order = new Orders(); // Creating a new order!
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(totalAmount);

        List<OrderItems> orderItems=new ArrayList<>();
        List<OrderItemsDTO> orderItemsDTOS=new ArrayList<>();

        for(Map.Entry<Long, Integer>entry: productQuantities.entrySet()){
            Product product = productRepo.findById(entry.getKey())
                    .orElseThrow(() ->new RuntimeException("Prduct not Found"));
            OrderItems orderItems1 = new OrderItems();
            orderItems1.setOrder(order);
            orderItems1.setProduct(product);
            orderItems1.setQuantity(entry.getValue());
            orderItems.add(orderItems1);

            orderItemsDTOS.add(new OrderItemsDTO(product.getName(), product.getPrice(), entry.getValue()));
        }

        order.setOrderItems(orderItems);
        Orders saveOrder = orderRepo.save(order);
        return new OrderDTO(saveOrder.getId(), saveOrder.getTotalAmount(), saveOrder.getStatus(), saveOrder.getOrderDate(),orderItemsDTOS);
    }


    public List<OrderDTO> getAllOrders(){
        List<Orders> orders = orderRepo.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders orders) {

        List<OrderItemsDTO> OrderItems = orders.getOrderItems().stream().map(item -> new OrderItemsDTO(
           item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity())).collect(Collectors.toList());
        return new OrderDTO(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getOrderDate(),
                orders.getUser()!=null ? orders.getUser().getName():"Unknown",
                orders.getUser()!=null ? orders.getUser().getEmail():"Unknown",
                OrderItems
        );
    }


    @Override
    public List<OrderDTO> getOrderByUser(Long userId) {
      Optional<User> userOp= userRepo.findById(userId);
      if(userOp.isEmpty()){
          throw new RuntimeException("User not found!");
      }
      User user = userOp.get();
      List<Orders> orderList =  orderRepo.findByUser(user);
      return orderList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }






}
