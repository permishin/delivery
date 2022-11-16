package com.example.deliveries.service;

import com.example.deliveries.model.CartModel;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;

public interface SessionService {
    CartModel getSession(@NonNull HttpServletRequest request);
}
