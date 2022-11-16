package com.example.deliveries.controller;

import com.example.deliveries.entity.Provider;
import com.example.deliveries.exception.EntityNotFoundException;
import com.example.deliveries.service.DeliveryService;
import com.example.deliveries.service.ProviderService;
import com.example.deliveries.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@AllArgsConstructor
@RequestMapping("/add_delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final SessionService sessionService;
    private final ProviderService providerService;

    @GetMapping
    public String getMain(HttpServletRequest request,
                          Model model) {
        model.addAttribute("title", "Создать поставку");
        model.addAttribute("cart", sessionService.getSession(request));
        return "add_delivery";
    }

    @GetMapping("/addProduct")
    public String listProductHandler(HttpServletRequest request,
                                     Model model,
                                     @RequestParam(value = "id", defaultValue = "") Long productId)
            throws EntityNotFoundException {
        model.addAttribute("cart", deliveryService.addProduct(request, productId));
        return "redirect:/add_delivery";
    }

    @PostMapping("/updateCount/{id}")
    public String updateCount(HttpServletRequest request,
                              @RequestParam Integer count,
                              @PathVariable(value = "id") Long id) throws EntityNotFoundException {
        deliveryService.updateCountProduct(request, count, id);
        return "redirect:/add_delivery";
    }

    @PostMapping("/{id}/deleteProductFromCart")
    public String remoteProduct(HttpServletRequest request,
                                @PathVariable(value = "id") Long id) throws EntityNotFoundException {
        deliveryService.deleteProductFromCart(request, id);
        return "redirect:/add_delivery";
    }

    @PostMapping("/makeOrder")
    public String makeOrder(HttpServletRequest request) {
        deliveryService.makeOrder(request);
        return "redirect:/";
    }

    @ModelAttribute("getAllProviders")
    public Iterable<Provider> getAllCommentsAsString() {
        return providerService.getAllProviders();
    }
}
