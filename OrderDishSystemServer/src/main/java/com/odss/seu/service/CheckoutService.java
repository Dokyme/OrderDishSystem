package com.odss.seu.service;

public interface CheckoutService {
    void onlineCheckout();

    void confirmCheckout(Integer orderId);
}
