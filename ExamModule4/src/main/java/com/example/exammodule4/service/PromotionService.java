package com.example.exammodule4.service;

import com.example.exammodule4.model.Promotion;
import com.example.exammodule4.repo.IPromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class PromotionService implements IPromotionService {
    @Autowired
    IPromotionRepo promotionRepo;
    @Override
    public List<Promotion> findAll() {
        return promotionRepo.findAll();
    }

    @Override
    public Optional<Promotion> findById(Long id) {
        return promotionRepo.findById(id);
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepo.save(promotion);
    }

    @Override
    public void deleteById(Long id) {
        promotionRepo.deleteById(id);
    }

    @Override
    public List<Promotion> search(Long discount, LocalDate startDate, LocalDate endDate) {
        return promotionRepo.search(discount,startDate,endDate);
    }
}
