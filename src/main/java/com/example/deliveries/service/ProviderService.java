package com.example.deliveries.service;

import com.example.deliveries.entity.Provider;
import com.example.deliveries.exception.EntityNotFoundException;
import lombok.NonNull;

public interface ProviderService {
    Iterable<Provider> getAllProviders();

    void addProvider(@NonNull String providerName);

    void addProductForProvider(@NonNull Long providerId, @NonNull Long productId) throws EntityNotFoundException;
}
