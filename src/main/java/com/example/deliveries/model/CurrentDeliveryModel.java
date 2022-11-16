package com.example.deliveries.model;

import com.example.deliveries.entity.Product;
import com.example.deliveries.entity.Status;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import static java.util.Objects.nonNull;

@Builder
@Getter
public class CurrentDeliveryModel {
    private Long id;

    private String time;

    private Status status;

    private Map<Product, Integer> productCount;

    public String getStatusDescription() {
        return nonNull(status) ? status.getDescriptionRu() : "";
    }
}
