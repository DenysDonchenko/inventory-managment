package com.example.inventorym.controller;

import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.dto.ProductCreateBO;
import com.example.inventorym.dto.ProductSearchBO;
import com.example.inventorym.service.impl.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller()
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getUsers(@RequestParam(defaultValue = "createdAt") String sort,
                           @RequestParam(defaultValue = "desc") String dir,
                           Model model, Authentication authentication) {

        var sortOrder = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        var products = productService.findAllByUser(authentication.getName(), sortOrder);

        model.addAttribute("newProduct", new ProductCreateBO());
        model.addAttribute("searchProduct", new ProductSearchBO());
        model.addAttribute("editProduct", new ProductBO());
        model.addAttribute("productsList", products);

        return "products";
    }

    @PostMapping("/")
    public String addProduct(ProductCreateBO productCreateBO, Authentication authentication) {
        productCreateBO.setEmail(authentication.getName());
        productService.createProduct(productCreateBO);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       Model model) {
        model.addAttribute("editProduct", productService.findById(id));
        return "productEdit";
    }

    @PostMapping("/edit")
    public String editSave(ProductBO productBO) {
        productService.updateProduct(productBO);
        return "redirect:/products";
    }

    @PostMapping("/search")
    public String searchProduct(ProductSearchBO productBO,
                                @RequestParam(defaultValue = "createdAt") String sort,
                                @RequestParam(defaultValue = "desc") String dir,
                                Model model, Authentication authentication) {
        var sortOrder = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        var products = productService.searchProduct(productBO, authentication.getName(), sortOrder);

        model.addAttribute("newProduct", new ProductCreateBO());
        model.addAttribute("editProduct", new ProductBO());
        model.addAttribute("searchProduct", new ProductSearchBO());
        model.addAttribute("productsList", products);
        return "products";
    }

}
