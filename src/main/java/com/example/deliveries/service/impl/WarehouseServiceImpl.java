package com.example.deliveries.service.impl;

import com.example.deliveries.model.WarehouseModel;
import com.example.deliveries.repository.WareHouseRepository;
import com.example.deliveries.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss");

    private final WareHouseRepository wareHouseRepository;

    @Override
    public List<WarehouseModel> getCurrentWarehouse() {
        var resultList = new ArrayList<WarehouseModel>();
        wareHouseRepository.findAll().forEach(entity ->
                resultList.add(
                        WarehouseModel.builder()
                                .id(entity.getId())
                                .startTime(dateTimeFormatter.format(entity.getStartTime()))
                                .endTime(dateTimeFormatter.format(entity.getEndTime()))
                                .warehouseProduct(entity.getWarehouseProduct())
                                .build()
                )
        );
        return resultList;
    }
}
