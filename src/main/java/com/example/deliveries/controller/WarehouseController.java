package com.example.deliveries.controller;

import com.example.deliveries.service.impl.WarehouseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    private WarehouseServiceImpl warehouseService;

    @GetMapping
    public String getWarehouse(Model model) {
        model.addAttribute("warehouse", warehouseService.getCurrentWarehouse());
        model.addAttribute("title", "Склад");
        return "warehouse";
    }
}
