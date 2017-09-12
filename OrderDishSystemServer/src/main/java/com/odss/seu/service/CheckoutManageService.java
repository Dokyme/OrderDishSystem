package com.odss.seu.service;

import com.odss.seu.vo.Checkout;
import com.odss.seu.vo.Order;

import java.util.List;

public interface CheckoutManageService {
    List<Checkout> queryCheckouts();
    void confirmCheckouted(Integer orderId);
    void deleteCheckout(Integer orderId);
}
