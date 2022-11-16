package com.example.deliveries.service;

import com.example.deliveries.exception.CurrentDeliveryNotFoundException;
import com.example.deliveries.model.CurrentDeliveryModel;

import java.util.List;

public interface CurrentDeliveriesService {
    List<CurrentDeliveryModel> getCurrentDeliveries();

    CurrentDeliveryModel getCurrentDelivery(Long id) throws CurrentDeliveryNotFoundException;

    CurrentDeliveryModel editStatus(Long id, String status) throws CurrentDeliveryNotFoundException;

    void closeDelivery(Long id) throws CurrentDeliveryNotFoundException;
}
