package com.projects.EcommerceApp.controller;

import com.projects.EcommerceApp.dto.AddToCartRequestDTO;
import com.projects.EcommerceApp.dto.CartDTO;
import com.projects.EcommerceApp.dto.CartItemDTO;
import com.projects.EcommerceApp.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    // 1️⃣ Add item to cart
    @PostMapping("/add")
    public ResponseEntity<CartDTO> addItemToCart( @Valid @RequestBody AddToCartRequestDTO request){
        CartDTO updatedCart = cartService.addItemToCart(request);
        return ResponseEntity.ok(updatedCart);
    }

    // 2️⃣ Get user's latest cart
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO>  getLatestCart(@PathVariable Long userId) {
        CartDTO cart = cartService.getLatestCartForUser(userId);
        return ResponseEntity.ok(cart);
    }

    // 3️⃣ Update item quantity
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItemDTO updatedItem = cartService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    // 4️⃣ Remove item from cart
    @DeleteMapping("/{userId}/item/{cartItemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long userId, @PathVariable Long cartItemId) {
        cartService.removeCartItemById(userId, cartItemId);
        return ResponseEntity.noContent().build();
    }

    // 5️⃣ Clear entire cart
    @DeleteMapping("/{userId}/clear/{cartId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId, @PathVariable Long cartId) {
        cartService.clearCart(userId, cartId);
        return ResponseEntity.noContent().build();
    }
}
