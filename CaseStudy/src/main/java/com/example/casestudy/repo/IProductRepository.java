package com.example.casestudy.repo;

import com.example.casestudy.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findByNameProductContainingIgnoreCase(String nameProduct);
//    List<Product> findByCategory_Id(Long categoryId);
//    List<Product> findByInStock (Boolean inStock);
//    List<Product> findByPriceBetween(Double min, Double max);
@Query("SELECT p FROM Product p WHERE " +
        "(:name IS NULL OR LOWER(p.nameProduct) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
        "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
        "(:inStock IS NULL OR p.inStock = :inStock) AND " +
        "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
        "(:maxPrice IS NULL OR p.price <= :maxPrice)")
Page<Product> searchAndFilter(
        @Param("name") String name,
        @Param("categoryId") Long categoryId,
        @Param("inStock") Boolean inStock,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        Pageable pageable
);
}
