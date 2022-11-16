package com.example.deliveries.service.impl;

import com.example.deliveries.model.CartModel;
import com.example.deliveries.service.SessionService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public CartModel getSession(@NonNull HttpServletRequest request) {
        HttpSession session = request.getSession();
        return CartModel.getSession(session);
    }
}
