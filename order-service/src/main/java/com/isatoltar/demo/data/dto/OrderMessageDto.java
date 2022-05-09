package com.isatoltar.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessageDto {
    Integer orderId;
    Integer userId;
    String orderStatus;
}
