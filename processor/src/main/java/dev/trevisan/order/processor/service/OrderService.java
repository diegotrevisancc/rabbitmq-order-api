package dev.trevisan.order.processor.service;

import dev.trevisan.order.processor.entity.Order;
import dev.trevisan.order.processor.entity.OrderItem;
import dev.trevisan.order.processor.repository.OrderItemRepository;
import dev.trevisan.order.processor.repository.OrderRepository;
import dev.trevisan.order.processor.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;
    private Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemService orderItemService, ProductService productService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemService = orderItemService;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
    }

    public void save(Order order) {
        productService.save(order.getItems());
        List<OrderItem> items = orderItemService.save(order.getItems());
        orderRepository.save(order);

        orderItemService.updatedOrderItem(items, order);
    }

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
