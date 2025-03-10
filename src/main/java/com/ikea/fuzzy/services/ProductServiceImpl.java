package com.ikea.fuzzy.services;

import com.ikea.fuzzy.dtos.Product;
import com.ikea.fuzzy.repo.ProductRepo;
import com.ikea.fuzzy.util.DamerauLevenshtein;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepo productRepo = new ProductRepo();

    @Override
    public Page<Product> getAllProducts(PageRequest pageRequest) {
        List<Product> products = productRepo.getProducts();
        int start = (int) pageRequest.getOffset(); // Get the starting index
        int end = Math.min((start + pageRequest.getPageSize()), products.size()); // Get the ending index

        List<Product> paginatedProducts = products.subList(start, end); // Create the sublist for pagination
        return new PageImpl<>(paginatedProducts, pageRequest, products.size());
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.addProduct(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepo.getProductById(id).orElse(null);
    }

    @Override
    public Page<Product> searchProducts(String keyword, PageRequest pageRequest) {
        List<Product> products = productRepo.getProducts();
        List<Product> productsByKeyWord = products.stream()
                .sorted((p1, p2) -> {
                    int dist1 = DamerauLevenshtein.compute(keyword, p1.name());
                    int dist2 = DamerauLevenshtein.compute(keyword, p2.name());
                    return Integer.compare(dist1, dist2);
                })// Limit results to top 10 closest matches
                .toList();

        int start = (int) pageRequest.getOffset(); // Get the starting index
        int end = Math.min((start + pageRequest.getPageSize()), products.size()); // Get the ending index

        List<Product> paginatedProducts = productsByKeyWord.subList(start, end); // Create the sublist for pagination
        return new PageImpl<>(paginatedProducts, pageRequest, productsByKeyWord.size());
    }
}
