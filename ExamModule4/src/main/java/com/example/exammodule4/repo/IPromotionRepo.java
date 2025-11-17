package com.example.exammodule4.repo;

import com.example.exammodule4.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPromotionRepo extends JpaRepository<Promotion,Long> {
    @Query("""
    SELECT p FROM Promotion p
    WHERE (:discount IS NULL OR p.discount = :discount)
      AND (:startDate IS NULL OR p.startDate >= :startDate)
      AND (:endDate IS NULL OR p.endDate <= :endDate)
""")
    List<Promotion> search(
            @Param("discount") Long discount,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
