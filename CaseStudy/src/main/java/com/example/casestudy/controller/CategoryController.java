package com.example.casestudy.controller;

import com.example.casestudy.model.Category;
import com.example.casestudy.model.Product;
import com.example.casestudy.repo.ICategoryRepository;
import com.example.casestudy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public String listCategories(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("categories",categoryService.findAllCategories());
        return "category/list";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category",new Category());
        return "category/create";
    }
    @PostMapping("/create")
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryService.createCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findCategoryById(id);
        if (category.isPresent()) {
            ModelAndView mav = new ModelAndView("category/update");
            mav.addObject("category",category.get());
            return mav;
        }else {
            return new ModelAndView("404");
        }
    }
    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.updateCategory(category);
        redirectAttributes.addFlashAttribute("message","Category updated successfully");
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id")Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategoryById(id);
        redirectAttributes.addFlashAttribute("message","Category deleted successfully");
        return "redirect:/categories";
    }
}
