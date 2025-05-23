package com.projects.EcommerceApp.dto;

import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private long id;

    @Positive(message = "Total amount must be positive")
    @Max(value = 1_000_000, message = "Total amount cannot exceed 1,000,000")
    private double totalAmount;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "Status must be PENDING, CONFIRMED, or CANCELLED")
    private String status;

    @NotNull(message = "Order date is required")
    @PastOrPresent(message = "Order date cannot be in the future")
    private Date orderDate;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemsDTO> orderItems;

    public OrderDTO(long id, double totalAmount, String status, Date orderDate, String userName, String email, List<OrderItemsDTO> orderItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.userName = userName;
        this.email = email;
        this.orderItems = orderItems;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderItemsDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDTO(long id, double totalAmount, String status, Date orderDate, List<OrderItemsDTO> orderItems) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }


}
