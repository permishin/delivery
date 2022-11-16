package com.example.deliveries.model;

import com.example.deliveries.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@Builder
@Getter
public class WarehouseModel {

    private Long id;

    private String startTime;
    private String endTime;

    private Map<Product, Integer> warehouseProduct;

    public Double getTotalWeight() {
        var totalWeight = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : warehouseProduct.entrySet()) {
            Product k = entry.getKey();
            var v = new BigDecimal(entry.getValue());
            totalWeight = totalWeight.add(BigDecimal.valueOf(k.getWeight()).multiply(v));
        }
        return totalWeight.doubleValue();
    }

    public Double getTotalPrice() {
        var totalPrice = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : warehouseProduct.entrySet()) {
            Product k = entry.getKey();
            var v = new BigDecimal(entry.getValue());
            totalPrice = totalPrice.add(BigDecimal.valueOf(k.getPrice()).multiply(v));
        }
        return totalPrice.doubleValue();
    }

    public Integer getTotalCount() {
        var totalCount = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : warehouseProduct.entrySet()) {
            totalCount = totalCount.add(BigDecimal.valueOf(entry.getValue()));
        }
        return totalCount.intValue();
    }
}

