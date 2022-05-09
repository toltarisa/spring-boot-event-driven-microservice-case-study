package com.isatoltar.demo.service;

import com.isatoltar.demo.data.OrderStatus;
import com.isatoltar.demo.data.dto.OrderRequestDTO;
import com.isatoltar.demo.data.model.Order;

public interface OrderService {

    void createOrder(OrderRequestDTO order);

    Order getOrderBy(Integer id);

    void updateOrder(Integer orderId, OrderStatus status);
}
