package com.projects.EcommerceApp.service;

import com.projects.EcommerceApp.dto.AddToCartRequestDTO;
import com.projects.EcommerceApp.dto.CartDTO;
import com.projects.EcommerceApp.dto.CartItemDTO;
import com.projects.EcommerceApp.model.Cart;
import com.projects.EcommerceApp.model.CartItems;
import com.projects.EcommerceApp.model.Product;
import com.projects.EcommerceApp.model.User;
import com.projects.EcommerceApp.repository.CartItemRepository;
import com.projects.EcommerceApp.repository.CartRepository;
import com.projects.EcommerceApp.repository.ProductRepository;
import com.projects.EcommerceApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    // Add item to user's latest/open cart
    @Override
    public CartDTO addItemToCart(AddToCartRequestDTO  request ) {
        User user = userRepo.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("User not found"));

        Product product = productRepo.findById(request.getProductId()).orElseThrow(()-> new RuntimeException ("Product not found"));

        Cart cart = getOrCreateOpenCartForUser(user);

        Optional<CartItems> existingItemopt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId())).findFirst();

        if(existingItemopt.isPresent()){
            CartItems existingItems = existingItemopt.get();
            existingItems.setQuantity(existingItems.getQuantity() + request.getQuantity());
            existingItems.setSubTotal(existingItems.getQuantity() * product.getPrice());
            cartItemRepo.save(existingItems);
        }else{
            CartItems newItem = new CartItems();
            newItem.setProduct(product);
            newItem.setQuantity(request.getQuantity());
            newItem.setSubTotal(request.getQuantity() * product.getPrice());
            newItem.setCart(cart);

            cart.getItems().add(newItem);
            cartItemRepo.save(newItem);
        }

        updateCartTotal(cart);
        Cart savedCart = cartRepo.save(cart);
        return convertToCartDto(savedCart);
    }


    // Get latest/open cart for a user
    @Override
    public CartDTO getLatestCartForUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // If you have CartStatus, filter by status = OPEN here
        // For now, return last cart or create new cart if none
        Cart cart = getOrCreateOpenCartForUser(user);
        return convertToCartDto(cart);
    }

    // Update quantity of a CartItem
    @Override
    public CartItemDTO updateCartItemQuantity(Long cartItemId, int newQuantity) {
        CartItems item =cartItemRepo.findById(cartItemId).orElseThrow(()-> new RuntimeException("CartItem not found"));
        if(newQuantity <= 0){
            // If the quantity is zero or less thanzero then remove it
            cartItemRepo.delete(item);
            updateCartTotal(item.getCart());
            return null;
        } else{
            item.setQuantity(newQuantity);
            item.setSubTotal(newQuantity * item.getProduct().getPrice());
            cartItemRepo.save(item);

            // Update cart item total price:
            updateCartTotal(item.getCart());
            return convertToCartItemDto(item);
        }
    }
    // Remove a CartItem by id
    @Override
    public void removeCartItemById(Long userId, Long cartItemId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found."));
        CartItems item = cartItemRepo.findById(cartItemId).orElseThrow(()->new RuntimeException("CartItems not found"));

        if (item.getCart().getUser().getId() != userId) {
            throw new RuntimeException("CartItem does not belong to the user");
        }else{
            Cart cart = item.getCart();
            cart.getItems().remove(item);

            cartItemRepo.delete(item);

            updateCartTotal(cart);
            cartRepo.save(cart);
        }

    }
    // Clear all items in a cart
    @Override
    public void clearCart(Long userId, Long cartId) {
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Cart cart = cartRepo.findById(cartId).orElseThrow(()->new RuntimeException("Cart not found."));
        if(cart.getUser().getId() != userId){
            throw new RuntimeException("Cart does not belong to the user");
        }else{
            cart.getItems().clear();
            updateCartTotal(cart);
            cartRepo.save(cart);
        }

    }

    // Helper methods - private
    private Cart getOrCreateOpenCartForUser(User user) {
        if(user.getCarts() != null &&  !user.getCarts().isEmpty()){
            return user.getCarts().get(user.getCarts().size()-1); // gets the list of carts
            // and with the hlp of size() counts the number carts the user have and then returns the last cart
        } else{
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setTotalPrice(0.0);
            return cartRepo.save(newCart);
        }
    }

    private void updateCartTotal(Cart cart) {
        double total = 0.0;
        for (CartItems item : cart.getItems()) {
            total += item.getSubTotal();
        }
        cart.setTotalPrice(total);
    }


    public CartDTO convertToCartDto(Cart cart){
        CartDTO dto = new CartDTO();
        dto.setCartId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setTotalPrice(cart.getTotalPrice());

        List<CartItemDTO> itemDtos = cart.getItems().stream().map(this::convertToCartItemDto).toList();
        dto.setItems(itemDtos);
        return dto;
    }

    private CartItemDTO convertToCartItemDto(CartItems item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setItemId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setQuantity(item.getQuantity());
        dto.setSubTotal(item.getSubTotal());
        return dto;
    }

    

}
