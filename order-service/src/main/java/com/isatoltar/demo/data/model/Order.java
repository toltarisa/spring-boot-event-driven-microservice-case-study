package com.isatoltar.demo.data.model;

import com.isatoltar.demo.data.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "status")
    String status = OrderStatus.ORDER_CREATED.getValue();
}
