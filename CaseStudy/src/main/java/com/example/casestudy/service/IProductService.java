package com.example.casestudy.service;

import com.example.casestudy.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Optional<Product> findProductById(Long id);
    void deleteProductById(Long id);
    List<Product> findAllProducts();
//    List<Product> findAllProductsByName(String name);

}
