package com.example.deliveries.controller;

import com.example.deliveries.entity.Product;
import com.example.deliveries.entity.Provider;
import com.example.deliveries.exception.EntityNotFoundException;
import com.example.deliveries.service.ProductService;
import com.example.deliveries.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/settings")
public class SettingsController {

    private ProviderService providerService;
    private ProductService productService;

    @GetMapping()
    public String getSettings(Model model) {
        model.addAttribute("title", "Настройки");
        return "settings";
    }

    @PostMapping("/addProvider")
    public String addProvider(@RequestParam String providerName) {
        providerService.addProvider(providerName);
        return "redirect:/settings";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String productName,
                             @RequestParam Double productWeight,
                             @RequestParam Double productPrice) {
        productService.addProduct(productName, productWeight, productPrice);
        return "redirect:/settings";
    }

    @PostMapping("/addProductForProvider")
    public String addProductForProvider(@RequestParam Long providerId,
                                        @RequestParam Long productId) throws EntityNotFoundException {
        providerService.addProductForProvider(providerId, productId);
        return "redirect:/settings";
    }

    @ModelAttribute("getAllProviders")
    public Iterable<Provider> getAllCommentsAsString() {
        return providerService.getAllProviders();
    }

    @ModelAttribute("getAllProducts")
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
