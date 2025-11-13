package com.example.casestudy.service;

import com.example.casestudy.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Optional<Product> findProductById(Long id);
    void deleteProductById(Long id);
    Page<Product> findAllProducts(Pageable pageable);
//    List<Product> findAllProductsByName(String name);
Page<Product> searchAndFilter(String name, Long categoryId, Boolean inStock, Double minPrice, Double maxPrice, String sortField, String sortDir,int page, int size);

}
