package com.example.casestudy.controller;

import com.example.casestudy.model.Product;
import com.example.casestudy.service.ICategoryService;
import com.example.casestudy.service.IProductService;
import com.example.casestudy.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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

//    @GetMapping
//    public String listProducts(Model model, @ModelAttribute("message") String message, Pageable pageable) {
//        model.addAttribute("products",productService.findAllProducts(pageable));
//        return "/products/list";
//    }
@GetMapping
public String listProducts(
        Model model,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "categoryId", required = false) Long categoryId,
        @RequestParam(value = "inStock", required = false) Boolean inStock,
        @RequestParam(value = "minPrice", required = false) Double minPrice,
        @RequestParam(value = "maxPrice", required = false) Double maxPrice,
        @RequestParam(value = "sortField", defaultValue = "id") String sortField,
        @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "5") int size
) {
    Page<Product> products = productService.searchAndFilter(
            name, categoryId, inStock, minPrice, maxPrice,
            sortField, sortDir, page, size
    );

    model.addAttribute("products", products);
    model.addAttribute("currentPage", products.getNumber() + 1);
    model.addAttribute("totalPages", products.getTotalPages());
    model.addAttribute("totalItems", products.getTotalElements());
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

    // giữ filter values để Thymeleaf giữ form search
    model.addAttribute("name", name);
    model.addAttribute("categoryId", categoryId);
    model.addAttribute("inStock", inStock);
    model.addAttribute("minPrice", minPrice);
    model.addAttribute("maxPrice", maxPrice);

    model.addAttribute("categories", categoryService.findAllCategories());

    return "products/list";
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
            model.addAttribute("categories", categoryService.findAllCategories());
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
    @GetMapping("/detail/{id}")
    public String detailProduct(@PathVariable("id")Long id, Model model) {
        Product product = productService.findProductById(id).orElse(null);
        model.addAttribute("product",product);
        return "products/detail";
    }
//    @GetMapping("/search-filter")
//    public String searchAndFilter(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "categoryId", required = false) Long categoryId,
//            @RequestParam(value = "inStock", required = false) Boolean inStock,
//            @RequestParam(value = "minPrice", required = false) Double minPrice,
//            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
//            @RequestParam(required = false, defaultValue = "price") String sortField,
//            @RequestParam(required = false, defaultValue = "asc") String sortDir,
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "size", defaultValue = "5") int size,
//            Model model
//    ) {
//        Page<Product> products = productService.searchAndFilter(name, categoryId, inStock, minPrice, maxPrice, sortField,sortDir,page,size);
//        model.addAttribute("products", products);
//
//        return "products/list";
//    }

}
