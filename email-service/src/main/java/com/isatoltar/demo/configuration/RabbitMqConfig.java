package com.isatoltar.demo.configuration;

import com.isatoltar.demo.service.EmailService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${demo.rabbit.queue-name}")
    String queueName;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    @Value("${spring.rabbitmq.port}")
    String port;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setMessageListener(new EmailService());

        return simpleMessageListenerContainer;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setPort(Integer.parseInt(port));
        cachingConnectionFactory.setHost(host);

        return cachingConnectionFactory;
    }
}
