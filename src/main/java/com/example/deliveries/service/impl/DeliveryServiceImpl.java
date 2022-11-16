package com.example.deliveries.service.impl;

import com.example.deliveries.entity.CurrentDelivery;
import com.example.deliveries.entity.Product;
import com.example.deliveries.entity.Provider;
import com.example.deliveries.entity.Status;
import com.example.deliveries.exception.EntityNotFoundException;
import com.example.deliveries.exception.ProductNotFoundException;
import com.example.deliveries.model.CartModel;
import com.example.deliveries.repository.CurrentDeliveryRepository;
import com.example.deliveries.repository.ProductRepository;
import com.example.deliveries.repository.ProviderRepository;
import com.example.deliveries.service.DeliveryService;
import com.example.deliveries.service.SessionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.ProviderNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private SessionService sessionService;
    private ProductRepository productRepo;
    private ProviderRepository providerRepo;
    private CurrentDeliveryRepository currentDeliveryRepo;

    @Override
    public CartModel addProduct(@NonNull HttpServletRequest request, @NonNull Long productId) throws EntityNotFoundException {
        CartModel bean = sessionService.getSession(request);
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));
        Provider provider = providerRepo.findById(product.getProvider().getId())
                .orElseThrow(() -> new ProviderNotFoundException("Provider with id " + product.getProvider().getId() + " not found"));
        product.setProvider(provider);
        bean.ifIdEqualsInCart(productId);
        if (!bean.idIsAddToCart(productId)) {
            bean.addItemProduct(product);
        }
        product.setCount(1);

        return bean;
    }

    public void updateCountProduct(@NonNull HttpServletRequest request, @NonNull Integer count, @NonNull Long id) throws EntityNotFoundException {
        HttpSession session = request.getSession();
        CartModel bean = CartModel.getSession(session);
        bean.getProducts().get(bean.numberOfProduct(id)).setCount(count);
        if (count == 0) {
            Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Not fount product with Id"));
            bean.deleteItemProduct(product);
        }
    }

    public void deleteProductFromCart(@NonNull HttpServletRequest request, @NonNull Long id) throws EntityNotFoundException {
        CartModel bean = sessionService.getSession(request);
        Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Not fount product with Id"));
        bean.deleteItemProduct(product);
    }

    public void makeOrder(@NonNull HttpServletRequest request) {
        HttpSession session = request.getSession();
        CartModel bean = CartModel.getSession(session);
        CurrentDelivery cd = new CurrentDelivery();
        Map<Product, Integer> map = new HashMap<>();
        for (Product product : bean.getProducts()) {
            map.put(product, product.getCount());
        }
        cd.setProductCount(map);
        cd.setStatus(Status.CREATED);
        cd.setTime(LocalDateTime.now());
        currentDeliveryRepo.save(cd);
        bean.deleteAll(bean);
    }
}
