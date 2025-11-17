package com.example.exammodule4.service;
import com.example.exammodule4.model.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPromotionService {
    List<Promotion> findAll();
    Optional<Promotion> findById(Long id);
    Promotion save(Promotion promotion);
    public void deleteById(Long id);
List<Promotion> search(Long discount, LocalDate startDate, LocalDate endDate);
}