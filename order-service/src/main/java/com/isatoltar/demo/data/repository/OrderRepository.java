package com.isatoltar.demo.data.repository;

import com.isatoltar.demo.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
