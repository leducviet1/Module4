package com.example.casestudy.service;

import com.example.casestudy.model.Product;
import com.example.casestudy.repo.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    IProductRepository repository;


    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }
    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(product.getId()==null || !repository.existsById(product.getId())) {
            throw new IllegalArgumentException("Không tìm thấy sản phẩm");
        }
        return repository.save(product);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

}
