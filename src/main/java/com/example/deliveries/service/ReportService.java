package com.example.deliveries.service;

import com.example.deliveries.model.WarehouseModel;
import lombok.NonNull;

import java.text.ParseException;
import java.util.List;

public interface ReportService {
    List<WarehouseModel> filterByDate(@NonNull String startDate, @NonNull String endDate) throws ParseException;
}
