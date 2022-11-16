package com.example.deliveries.service;

import com.example.deliveries.exception.EntityNotFoundException;
import com.example.deliveries.model.CartModel;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;

public interface DeliveryService {
    CartModel addProduct(@NonNull HttpServletRequest request, @NonNull Long productId) throws EntityNotFoundException;

    void updateCountProduct(@NonNull HttpServletRequest request, @NonNull Integer count, @NonNull Long id) throws EntityNotFoundException;

    void deleteProductFromCart(@NonNull HttpServletRequest request, @NonNull Long id) throws EntityNotFoundException;

    void makeOrder(@NonNull HttpServletRequest request);
}
