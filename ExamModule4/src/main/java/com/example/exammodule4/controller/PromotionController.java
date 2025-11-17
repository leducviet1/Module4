package com.example.exammodule4.controller;

import com.example.exammodule4.model.Promotion;
import com.example.exammodule4.service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/promotions")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public String list(Model model) {
        List<Promotion> promotions = promotionService.findAll();
        model.addAttribute("promotions", promotions);
        return "list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "create";
    }

    @PostMapping("/create")
    public String createPromotion(@Valid
                                  @ModelAttribute("promotion") Promotion promotion,
                                  BindingResult result,
                                  Model model)
    {
        LocalDate today = LocalDate.now();
        if(promotion.getStartDate()!=null && promotion.getStartDate().isBefore(today)) {
            result.rejectValue("startDate", "startDate.past", "Thời gian bắt đầu phải lớn hơn hiện tại");
        }
        if(promotion.getStartDate()!=null && promotion.getEndDate()!=null) {
            long days = ChronoUnit.DAYS.between(promotion.getStartDate(), promotion.getEndDate());
            if(days < 1){
                result.rejectValue("endDate","endDate.short","Thời gian kết thúc phải lớn hơn bắt đầu 1 ngày");
            }
        }
        if(result.hasErrors()) {
            return "create";
        }
        promotionService.save(promotion);
        return "redirect:/promotions";
    }
    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable("id") Long id) {
        promotionService.deleteById(id);
        return "redirect:/promotions";
    }
    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) Long discount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        model.addAttribute("promotions",
                promotionService.search(discount, startDate, endDate));
        model.addAttribute("discount", discount);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "list";
    }
}
