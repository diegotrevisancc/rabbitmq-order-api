package dev.trevisan.order.api.service;

import dev.trevisan.order.api.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Value("${rabbitmq.exchange.name}") //get value from application.properties
    private String exchangeName;

    private final RabbitTemplate rabbitTemplate;

    public OrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Order queueOrder(Order order) {
        rabbitTemplate.convertAndSend(exchangeName,
                "",order); //uses messageConverter config, we are using fanout so it doesnt need any routingkey
        logger.info("Order queued: {}", order);
        return order;
    }
}
