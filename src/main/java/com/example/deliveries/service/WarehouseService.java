package com.example.deliveries.service;

import com.example.deliveries.model.WarehouseModel;
import lombok.NonNull;

import java.util.List;

public interface WarehouseService {
    List<@NonNull WarehouseModel> getCurrentWarehouse();
}
