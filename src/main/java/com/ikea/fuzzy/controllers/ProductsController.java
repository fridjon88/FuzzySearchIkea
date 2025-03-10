package com.ikea.fuzzy.controllers;

import com.ikea.fuzzy.dtos.Product;
import com.ikea.fuzzy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/products")
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,

                                        @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.getAllProducts(pageRequest);
    }

    @GetMapping("/search")
    public Page<Product> searchProducts(@RequestParam String search,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.searchProducts(search, pageRequest);
    }

    @GetMapping("/products/:id")
    public Product getProductById(@RequestParam Long id) {
        return productService.getProduct(id);
    }

}
