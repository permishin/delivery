package com.example.deliveries.service.impl;

import com.example.deliveries.entity.Product;
import com.example.deliveries.entity.Provider;
import com.example.deliveries.exception.EntityNotFoundException;
import com.example.deliveries.exception.ProductNotFoundException;
import com.example.deliveries.repository.ProductRepository;
import com.example.deliveries.repository.ProviderRepository;
import com.example.deliveries.service.ProviderService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private ProviderRepository providerRepo;
    private ProductRepository productRepo;

    @Override
    public Iterable<Provider> getAllProviders() {
        return providerRepo.findAll();
    }

    @Override
    public void addProvider(@NonNull String providerName) {
        Provider provider = new Provider();
        provider.setProviderName(providerName);
        providerRepo.save(provider);
    }

    @Override
    public void addProductForProvider(@NonNull Long providerId, @NonNull Long productId) throws EntityNotFoundException {
        Provider provider = providerRepo.findById(providerId)
                .orElseThrow(() -> new ProviderNotFoundException("Not found provider with Id"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Not found product  with Id"));
        product.setProvider(provider);
        provider.getProductList().add(product);
        productRepo.save(product);
        providerRepo.save(provider);
    }
}
