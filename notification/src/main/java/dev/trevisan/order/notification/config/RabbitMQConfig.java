package dev.trevisan.order.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.dlx.name}")
    private String exchangeNameDlx;
    @Value("${rabbitmq.queue.dlq.name}")
    private String queueNameDlq;

    @Bean
    public FanoutExchange orderExchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public FanoutExchange orderDlxExchange() {
        return new FanoutExchange(exchangeNameDlx);
    }

    @Bean
    public Queue notificationQueue() {
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-dead-letter-exchange", exchangeName);

        return new Queue(queueName, true, false, false, arguments);
    }

    @Bean
    public Queue notificationDlqQueue() {
        return new Queue(queueNameDlq);
    }

    @Bean
    public Binding notificationBinding() {
        return BindingBuilder.bind(notificationQueue()).to(orderExchange()); //bind notification queue into order exchange
    }

    @Bean
    public Binding notificationDlqBinding() {
        return BindingBuilder.bind(notificationDlqQueue()).to(orderDlxExchange());
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() { //convert JSON into Java Object
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
