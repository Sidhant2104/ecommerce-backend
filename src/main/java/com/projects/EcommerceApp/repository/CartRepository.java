package com.projects.EcommerceApp.repository;

import com.projects.EcommerceApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Simple method using Spring Data naming convention
    Optional<Cart> findByUserId(Long userId);

}
