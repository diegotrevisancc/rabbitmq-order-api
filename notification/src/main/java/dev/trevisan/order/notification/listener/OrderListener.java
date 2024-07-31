package dev.trevisan.order.notification.listener;

import dev.trevisan.order.notification.entity.Order;
import dev.trevisan.order.notification.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private EmailService service;

    public OrderListener(EmailService emailService) {
        this.service = emailService;
    }

    @RabbitListener(queues = "order.v1.order-created-send-notification") //will listen to all items in this queue, automatically ack
    public void sendNotification(Order order) {
        logger.info("Notification dispatched, order: {}", order.toString());
        service.sendEmail(order);
        logger.info("Notification email sent to: {}", order.getCustomerEmail());
    }
}
