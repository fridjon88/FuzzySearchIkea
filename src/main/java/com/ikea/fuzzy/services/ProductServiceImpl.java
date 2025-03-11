package com.ikea.fuzzy.services;

import com.ikea.fuzzy.dtos.Product;
import com.ikea.fuzzy.repo.ProductRepo;
import com.ikea.fuzzy.util.DamerauLevenshtein;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        return productRepo.getProductById(id).orElseThrow();
    }

    @Override
    public Page<Product> searchProducts(String keyword, PageRequest pageRequest) {
        List<Product> products = productRepo.getProducts();

        // search in name, description and category
        List<Product> productsByKeyWord = products.stream()
                .sorted(Comparator.comparingInt(p -> {
                    int nameDist = DamerauLevenshtein.compute(keyword, p.name());
                    int descDist = DamerauLevenshtein.compute(keyword, p.description());
                    int categoryDist = DamerauLevenshtein.compute(keyword, p.category());

                    // Use the minimum distance
                    return Math.min(nameDist, Math.min(descDist, categoryDist));
                }))
                .toList();

        int start = (int) pageRequest.getOffset(); // Get the starting index
        int end = Math.min((start + pageRequest.getPageSize()), products.size()); // Get the ending index

        List<Product> paginatedProducts = productsByKeyWord.subList(start, end); // Create the sublist for pagination
        return new PageImpl<>(paginatedProducts, pageRequest, productsByKeyWord.size());
    }
}
