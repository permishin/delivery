package com.example.deliveries.service.impl;

import com.example.deliveries.entity.CurrentDelivery;
import com.example.deliveries.entity.Product;
import com.example.deliveries.entity.Status;
import com.example.deliveries.entity.Warehouse;
import com.example.deliveries.exception.CurrentDeliveryNotFoundException;
import com.example.deliveries.model.CurrentDeliveryModel;
import com.example.deliveries.repository.CurrentDeliveryRepository;
import com.example.deliveries.repository.WareHouseRepository;
import com.example.deliveries.service.CurrentDeliveriesService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CurrentDeliveriesServiceImpl implements CurrentDeliveriesService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss");

    private CurrentDeliveryRepository currentDeliveryRepo;
    private WareHouseRepository wareHouseRepo;

    @Override
    public List<CurrentDeliveryModel> getCurrentDeliveries() {
        var resultList = new ArrayList<CurrentDeliveryModel>();
        currentDeliveryRepo.findAll().forEach(entity ->
                resultList.add(
                        CurrentDeliveryModel.builder()
                                .id(entity.getId())
                                .status(entity.getStatus())
                                .productCount(entity.getProductCount())
                                .time(dateTimeFormatter.format(entity.getTime()))
                                .build()
                )
        );
        return resultList;
    }

    @Override
    public CurrentDeliveryModel getCurrentDelivery(@NonNull Long id) throws CurrentDeliveryNotFoundException {
        CurrentDelivery currentDelivery = currentDeliveryRepo.findById(id)
                .orElseThrow(() -> new CurrentDeliveryNotFoundException("Current Delivery with id " + id + " not found"));
        return CurrentDeliveryModel.builder()
                .id(currentDelivery.getId())
                .status(currentDelivery.getStatus())
                .productCount(currentDelivery.getProductCount())
                .time(dateTimeFormatter.format(currentDelivery.getTime()))
                .build();
    }

    @Override
    public CurrentDeliveryModel editStatus(@NonNull Long id, @NonNull String status) throws CurrentDeliveryNotFoundException {
        CurrentDelivery currentDelivery = currentDeliveryRepo.findById(id)
                .orElseThrow(() -> new CurrentDeliveryNotFoundException("Current Delivery with id " + id + " not found"));
        Status statusEnum = Status.valueOf(status);
        currentDelivery.setStatus(statusEnum);
        currentDeliveryRepo.save(currentDelivery);
        return CurrentDeliveryModel.builder()
                .status(currentDelivery.getStatus())
                .build();
    }

    @Override
    public void closeDelivery(@NonNull Long id) throws CurrentDeliveryNotFoundException {
        CurrentDelivery currentDelivery = currentDeliveryRepo.findById(id).orElseThrow(() -> new CurrentDeliveryNotFoundException("Current Delivery with id " + id + " not found"));
        Warehouse warehouse = new Warehouse();
        warehouse.setEndTime(LocalDateTime.now());
        warehouse.setStartTime(currentDelivery.getTime());
        Map<Product, Integer> map = new HashMap<>(currentDelivery.getProductCount());
        warehouse.setWarehouseProduct(map);
        wareHouseRepo.save(warehouse);
        currentDeliveryRepo.delete(currentDelivery);
    }
}
