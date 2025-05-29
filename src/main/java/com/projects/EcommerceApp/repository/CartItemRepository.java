package com.projects.EcommerceApp.repository;

import com.projects.EcommerceApp.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {

}
