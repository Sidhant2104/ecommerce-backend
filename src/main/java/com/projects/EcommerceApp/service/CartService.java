package com.projects.EcommerceApp.service;

import com.projects.EcommerceApp.dto.AddToCartRequestDTO;
import com.projects.EcommerceApp.dto.CartDTO;
import com.projects.EcommerceApp.dto.CartItemDTO;

public interface CartService {

    //* Add item to user's latest/open cart
    CartDTO addItemToCart(AddToCartRequestDTO request);

    //* Get user's latest open cart or create new cart
    CartDTO getLatestCartForUser(Long userId);

    //* Update quantity of a cart item
    CartItemDTO updateCartItemQuantity(Long cartItemId, int newQuantity);

    //* Remove a cart item by its ID
    void removeCartItemById(Long userId, Long cartItemId);

    //* Clear all items from the cart
    void clearCart(Long userId, Long cartId);
}
