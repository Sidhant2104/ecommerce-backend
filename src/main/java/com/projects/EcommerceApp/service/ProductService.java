package com.projects.EcommerceApp.service;

import com.projects.EcommerceApp.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(Long id);

    public Product addProduct(Product product);

    public void deleteProduct(Long id);
}
