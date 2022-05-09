package com.isatoltar.demo.service;

import com.isatoltar.demo.data.OrderStatus;
import com.isatoltar.demo.data.dto.OrderMessageDto;
import com.isatoltar.demo.data.dto.OrderRequestDTO;
import com.isatoltar.demo.data.model.Order;
import com.isatoltar.demo.data.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Value("${demo.rabbit.queue-name}")
    private String queueName;

    @Value("${demo.rabbit.exchange}")
    private String exchange;

    @Value("${demo.rabbit.routing-key}")
    private String routingKey;

    final OrderRepository orderRepository;
    final RabbitTemplate rabbitTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderRepository = orderRepository;
    }

    @Override
    public void createOrder(OrderRequestDTO dto) {

        Order order = new Order();
        order.setUserId(dto.getUserId());

        orderRepository.save(order);

        OrderMessageDto messageDto = new OrderMessageDto(
          order.getId(),
          order.getUserId(),
          order.getStatus()
        );

        rabbitTemplate.convertAndSend(exchange, routingKey, messageDto);
    }

    @Override
    public Order getOrderBy(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Unable to find order with id = " + id));
    }

    @Override
    public void updateOrder(Integer id, OrderStatus status) {

        if (status != null && status.equals(OrderStatus.ORDER_CREATED))
            throw new IllegalStateException("Cannot update order status to order_created");

        Order order = getOrderBy(id);

        boolean orderUpdated = false;

        if (status != null && !order.getStatus().equals(status.getValue())) {
            orderUpdated = true;
            order.setStatus(status.getValue());
        }

        if (orderUpdated) {
            orderRepository.save(order);
            rabbitTemplate.convertAndSend(
                    exchange,
                    routingKey,
                    new OrderMessageDto(
                            order.getId(),
                            order.getUserId(),
                            order.getStatus()
                    )
            );
        }
    }
}
