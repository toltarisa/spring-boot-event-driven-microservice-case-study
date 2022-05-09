package com.isatoltar.demo.controller;

import com.isatoltar.demo.data.OrderStatus;
import com.isatoltar.demo.data.dto.OrderRequestDTO;
import com.isatoltar.demo.service.OrderService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {

    final OrderService orderService;

    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO dto) {

        orderService.createOrder(dto);

        log.info("Order created");

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Integer orderId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderBy(orderId));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer orderId,
                                         @RequestParam(required = false) String orderStatus) {

        orderService.updateOrder(
                orderId,
                OrderStatus.fromValue(orderStatus)
        );

        return ResponseEntity.noContent().build();
    }
}
