package com.example.deliveries.exception;

public class CurrentDeliveryNotFoundException extends EntityNotFoundException {

    public CurrentDeliveryNotFoundException(String message) {
        super(message);
    }
}
