package com.example.casestudy.controller;

import com.example.casestudy.model.Product;
import com.example.casestudy.service.ICategoryService;
import com.example.casestudy.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public String listProducts(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("products",productService.findAllProducts());
        return "/products/list";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product",new Product());
        model.addAttribute("categories",categoryService.findAllCategories());
        return "/products/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }


    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()) {
            ModelAndView mav = new ModelAndView("products/update");
            mav.addObject("product",product.get());
            return mav;
        }else {
            return new ModelAndView("404");
        }
    }
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message","Product updated successfully");
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProductById(id);
        redirectAttributes.addFlashAttribute("message","Product deleted successfully");
        return "redirect:/products";
    }
}
