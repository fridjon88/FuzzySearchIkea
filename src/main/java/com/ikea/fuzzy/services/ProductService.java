package com.ikea.fuzzy.services;

import com.ikea.fuzzy.dtos.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {
    Page<Product> getAllProducts(PageRequest pageRequest);

    Product addProduct(Product product);

    Product getProduct(Long id);

    Page<Product> searchProducts(String keyword, PageRequest pageRequest);

}
