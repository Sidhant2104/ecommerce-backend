package com.projects.EcommerceApp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<CartItems> items = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)  // Foreign Key reference
        private User user;

        private Double totalPrice;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public List<CartItems> getItems() {
                return items;
        }

        public void setItems(List<CartItems> items) {
                this.items = items;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Double getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
                this.totalPrice = totalPrice;
        }
}
