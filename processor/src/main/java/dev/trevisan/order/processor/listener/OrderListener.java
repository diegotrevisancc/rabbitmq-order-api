package dev.trevisan.order.processor.listener;

import dev.trevisan.order.processor.entity.Order;
import dev.trevisan.order.processor.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);

    @RabbitListener(queues = "order.v1.order-created-process-order")
    public void saveOrder(Order order) {
        order.setStatus(Status.PROCESSED);

        logger.info("Order processed {}", order.toString());
    }
}
