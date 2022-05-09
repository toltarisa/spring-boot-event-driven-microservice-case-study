package com.isatoltar.demo.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum OrderStatus {
    ORDER_CREATED("order_created"),
    ORDER_CANCELLED("order_cancelled"),
    ORDER_DELIVERED("order_delivered"),
    ORDER_RETURN("order_return");

    final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus fromValue(String orderStatus) throws IllegalArgumentException {
        return Arrays.stream(OrderStatus.values())
                .filter(v -> v.getValue().equals(orderStatus))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
