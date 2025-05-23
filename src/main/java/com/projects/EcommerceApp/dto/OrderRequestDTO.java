package com.projects.EcommerceApp.dto;

import jakarta.validation.constraints.*;
import java.util.Map;

public class OrderRequestDTO {

    @NotEmpty(message = "Product quantities cannot be empty")
    private Map<@Positive(message = "Product ID must be positive") Long,
            @Positive(message = "Quantity must be greater than zero") Integer> productQuantities;

    @Positive(message = "Total amount must be positive")
    @Max(value = 1_000_000, message = "Total amount cannot exceed 1,000,000")
    private double totalAmount;

    public Map<Long, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Long, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

