package com.example.casestudy.service;

import com.example.casestudy.model.Product;
import com.example.casestudy.repo.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    IProductRepository repository;


    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Product> searchAndFilter(String name, Long categoryId, Boolean inStock, Double minPrice, Double maxPrice,String sortField,String sortDir, int page,int size)
    {
        if(sortField == null || sortField.isEmpty()){
            sortField = "id";
        }
        Sort sort = Sort.by(sortField);
        if("desc".equals(sortDir)){
            sort = sort.descending();
        }else if("asc".equals(sortDir)){
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of( page - 1, size, sort );
        return  repository.searchAndFilter(name, categoryId, inStock, minPrice, maxPrice,pageable);

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
