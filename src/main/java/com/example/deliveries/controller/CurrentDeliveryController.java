package com.example.deliveries.controller;

import com.example.deliveries.entity.Status;
import com.example.deliveries.exception.CurrentDeliveryNotFoundException;
import com.example.deliveries.model.CurrentDeliveryModel;
import com.example.deliveries.service.CurrentDeliveriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class CurrentDeliveryController {

    private CurrentDeliveriesService currentDeliveriesService;

    @GetMapping
    public String getMain(Model model) {
        model.addAttribute("title", "Текущие поставки");
        return "current_delivery";
    }

    @GetMapping("/{id}")
    public String getDeliveryId(@PathVariable(value = "id") Long id,
                                Model model) throws CurrentDeliveryNotFoundException {
        model.addAttribute("currentDelivery", currentDeliveriesService.getCurrentDelivery(id));
        model.addAttribute("statuses", Status.values());
        return "edit_delivery";
    }

    @PostMapping("/{id}/editStatus")
    public String editStatus(@PathVariable(value = "id") Long id,
                             @RequestParam String status,
                             Model model) throws CurrentDeliveryNotFoundException {
        model.addAttribute("currentDelivery", currentDeliveriesService.editStatus(id, status));
        return "redirect:/{id}";
    }

    @PostMapping("/{id}/closeDelivery")
    public String closeDelivery(@PathVariable(value = "id") Long id) throws CurrentDeliveryNotFoundException {
        currentDeliveriesService.closeDelivery(id);
        return "redirect:/";
    }

    @ModelAttribute("getAllCurrentDeliveries")
    public List<CurrentDeliveryModel> getAllCommentsAsString() {
        return currentDeliveriesService.getCurrentDeliveries();
    }

}
