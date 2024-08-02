package dev.trevisan.order.processor.service;

import dev.trevisan.order.processor.entity.Order;
import dev.trevisan.order.processor.entity.OrderItem;
import dev.trevisan.order.processor.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void save(List<OrderItem> items) {
        orderItemRepository.saveAll(items);
    }

    private void save(OrderItem item) {
        orderItemRepository.save(item);
    }

    public void updatedOrderItem(List<OrderItem> items, Order order) {
        items.forEach(item -> {
            item.setOrder(order); // setting order fk
            this.save(item);
        });
    }

}
