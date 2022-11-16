package com.example.deliveries.controller;

import com.example.deliveries.entity.Provider;
import com.example.deliveries.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/suppliers")
public class SuppliersController {

    private ProviderService providerService;

    @GetMapping
    public String getMain(Model model) {
        model.addAttribute("title", "Все поставщики и продукты");
        return "suppliers";
    }

    @ModelAttribute("getAllProviders")
    public Iterable<Provider> getAllCommentsAsString() {
        return providerService.getAllProviders();
    }
}
