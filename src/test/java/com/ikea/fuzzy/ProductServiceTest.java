package com.ikea.fuzzy;


import com.ikea.fuzzy.dtos.Product;
import com.ikea.fuzzy.services.ProductService;
import com.ikea.fuzzy.services.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest {
    @ParameterizedTest
    @CsvSource({"Dob, Dog"})
    @CsvSource({"Akimal, Dog"})
    @CsvSource({"Bed, Bed"})
    public void searchProducts(String keyword, String expected) {
        // setup
        ProductService productService = new ProductServiceImpl();

        // act
        Page<Product> products = productService.searchProducts(keyword, PageRequest.of(0, 5));

        // assert
        Assertions.assertEquals(expected, products.getContent().getFirst().name());
    }

    @Test
    public void findProductById() {
        // setup
        ProductService productService = new ProductServiceImpl();

        // act
        Product product = productService.getProduct(30L);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(30L, product.id());
    }

    @Test
    public void findProductByIdNotFound() {
        // setup
        ProductService productService = new ProductServiceImpl();

        // act & assert
        assertThrows(NoSuchElementException.class, () -> productService.getProduct(31L));
    }

    // Should of course test all methods, with happy path tests and negative tests
}
