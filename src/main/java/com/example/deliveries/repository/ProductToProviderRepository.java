package com.example.deliveries.repository;

import com.example.deliveries.entity.ProductToProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductToProviderRepository extends CrudRepository<ProductToProvider, Long> {
}
