package com.example.deliveries.service;

import com.example.deliveries.entity.Product;
import lombok.NonNull;

public interface ProductService {
    Iterable<Product> getAllProducts();

    boolean addProduct(@NonNull String productName, @NonNull Double productWeight, @NonNull Double productPrice);
}
