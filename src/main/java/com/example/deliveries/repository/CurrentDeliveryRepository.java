package com.example.deliveries.repository;

import com.example.deliveries.entity.CurrentDelivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDeliveryRepository extends CrudRepository<CurrentDelivery, Long> {
}
