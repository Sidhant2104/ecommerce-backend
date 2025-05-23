package com.projects.EcommerceApp.dto;

import jakarta.validation.constraints.*;

public class OrderItemsDTO {

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String productName;

    @Positive(message = "Product price must be positive")
    @Max(value = 100000, message = "Product price cannot exceed 100,000")
    private double productPrice;

    @Positive(message = "Quantity must be greater than zero")
    @Min(value = 1, message = "At least 1 item must be ordered")
    private int quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItemsDTO(String productName, double productPrice, int quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}
