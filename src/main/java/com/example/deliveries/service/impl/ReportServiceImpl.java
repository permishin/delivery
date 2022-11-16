package com.example.deliveries.service.impl;


import com.example.deliveries.model.WarehouseModel;
import com.example.deliveries.repository.WareHouseRepository;
import com.example.deliveries.service.ReportService;
import com.example.deliveries.service.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss");
    private final WareHouseRepository wareHouseRepo;
    private final WarehouseService warehouseService;

    @Override
    public List<WarehouseModel> filterByDate(@NonNull String startDate, @NonNull String endDate) throws ParseException {
        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sDate = formatter.parse(startDate + " 00:00:00");
            Date eDate = formatter.parse(endDate + " 23:59:59");
            var resultList = new ArrayList<WarehouseModel>();
            wareHouseRepo.findDeliveriesByBetweenTime(sDate, eDate)
                    .forEach(entity ->
                            resultList.add(WarehouseModel.builder()
                                    .id(entity.getId())
                                    .startTime(dateTimeFormatter.format(entity.getStartTime()))
                                    .endTime(dateTimeFormatter.format(entity.getEndTime()))
                                    .warehouseProduct(entity.getWarehouseProduct())
                                    .build()));
            return resultList;
        } else {
            return warehouseService.getCurrentWarehouse();
        }

    }
}
