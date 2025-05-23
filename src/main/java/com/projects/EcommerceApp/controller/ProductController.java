package com.projects.EcommerceApp.controller;

import com.projects.EcommerceApp.model.Product;
import com.projects.EcommerceApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/getAllProducts")
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById")
    public Product getProductById(@PathVariable Long Id) {
        return productService.getProductById(Id);
    }

    @PostMapping("/addProduct")
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }

}
