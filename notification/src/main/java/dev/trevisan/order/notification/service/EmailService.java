package dev.trevisan.order.notification.service;

import dev.trevisan.order.notification.entity.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("order-api@diegotrevisan.com");
        message.setTo(order.getCustomerEmail());
        message.setSubject("Order Notification - Trevisan Order Notification");
        message.setText(buildMessage(order));
        mailSender.send(message);
    }

    private String buildMessage(Order order) {
        String orderId = order.getId().toString();
        String customer = order.getCustomer();
        String value = String.valueOf(order.getSum());
        String status = order.getStatus().name();

        return String.format("Hey %s! Your order number %s" +
                ", was successfully received.\n" +
                "The total value of it is: %s \n" +
                "The order status is: %s \n", customer, orderId, value, status);

    }
}
