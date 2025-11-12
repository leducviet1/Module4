package com.example.casestudy.controller;

import com.example.casestudy.model.Product;
import com.example.casestudy.service.ICategoryService;
import com.example.casestudy.service.IProductService;
import com.example.casestudy.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private StorageService storageService;

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
    public String createProduct(
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                @RequestParam(value="imageProduct",required = false) MultipartFile imageProduct,
                                Model model)
    {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAllCategories());
            return "products/create"; // quay lại form thay vì lỗi
        }
        if (imageProduct != null) {
            try {
                String publicUrl = storageService.saveFile(imageProduct);
                product.setImage(publicUrl);
            }catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
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
    public String updateProduct(@ModelAttribute("product") Product product,
                                RedirectAttributes redirectAttributes,
                                @RequestParam(value = "imageProduct")  MultipartFile imageProduct)throws IOException {

        if(!imageProduct.isEmpty()){
            String newPublicUrl = storageService.saveFile(imageProduct);
            product.setImage(newPublicUrl);
        }else {
            Product oldProduct = productService.findProductById(product.getId()).orElse(null);
            if(oldProduct != null){
                product.setImage(oldProduct.getImage());
            }
        }
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
