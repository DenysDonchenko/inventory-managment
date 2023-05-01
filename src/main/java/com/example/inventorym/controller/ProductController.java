package com.example.inventorym.controller;

import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.dto.ProductCreateBO;
import com.example.inventorym.dto.ProductSearchBO;
import com.example.inventorym.facade.ProductFacade;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller()
@RequestMapping("/products")
public class ProductController {


    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping("")
    public String getUsers(@RequestParam(defaultValue = "createdAt") String sort,
                           @RequestParam(defaultValue = "desc") String dir,
                           Model model, Authentication authentication) {

        var sortOrder = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        var products = productFacade.findAllByUser(authentication.getName(), sortOrder);

        model.addAttribute("newProduct", new ProductCreateBO());
        model.addAttribute("searchProduct", new ProductSearchBO());
        model.addAttribute("editProduct", new ProductBO());
        model.addAttribute("productsList", products);

        return "products";
    }

    @PostMapping("/")
    public String addProduct(ProductCreateBO productCreateBO, Authentication authentication) {
        productCreateBO.setEmail(authentication.getName());
        productFacade.createProduct(productCreateBO);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       Model model) {
        model.addAttribute("editProduct", productFacade.findById(id));
        return "productEdit";
    }

    @PostMapping("/edit")
    public String editSave(ProductBO productBO) {
        productFacade.updateProduct(productBO);
        return "redirect:/products";
    }

    @PostMapping("/search")
    public String searchProduct(ProductSearchBO productBO,
                                @RequestParam(defaultValue = "createdAt") String sort,
                                @RequestParam(defaultValue = "desc") String dir,
                                Model model, Authentication authentication) {
        var sortOrder = dir.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        var products = productFacade.searchProduct(productBO, authentication.getName(), sortOrder);

        model.addAttribute("newProduct", new ProductCreateBO());
        model.addAttribute("editProduct", new ProductBO());
        model.addAttribute("searchProduct", new ProductSearchBO());
        model.addAttribute("productsList", products);
        return "products";
    }

}
