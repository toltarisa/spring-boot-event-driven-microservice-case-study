package com.isatoltar.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isatoltar.demo.dto.MessageResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String body = new String(message.getBody());
        ObjectMapper objectMapper = new ObjectMapper();

        MessageResponseDTO messageResponseDTO = null;
        try {
            messageResponseDTO = objectMapper.readValue(body, MessageResponseDTO.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        if (messageResponseDTO != null)
            log.info(
                    String.format(
                            "Notifying user with id = %d for order status change to %s with order id = %d",
                            messageResponseDTO.getUserId(),
                            messageResponseDTO.getOrderStatus(),
                            messageResponseDTO.getOrderId()
                    )
            );
    }
}
