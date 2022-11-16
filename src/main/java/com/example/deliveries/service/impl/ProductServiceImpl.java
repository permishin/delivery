package com.example.deliveries.service.impl;

import com.example.deliveries.entity.Product;
import com.example.deliveries.repository.ProductRepository;
import com.example.deliveries.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public boolean addProduct(@NonNull String productName, @NonNull Double productWeight, @NonNull Double productPrice) {
        Product product = new Product();
        product.setProductName(productName);
        product.setWeight(productWeight);
        product.setPrice(productPrice);
        productRepo.save(product);
        return true;
    }
}
