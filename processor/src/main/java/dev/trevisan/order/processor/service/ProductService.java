package dev.trevisan.order.processor.service;

import dev.trevisan.order.processor.entity.OrderItem;
import dev.trevisan.order.processor.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(List<OrderItem> items) {
        for (OrderItem item : items) {
            productRepository.save(item.getProduct());
        }
    }
}
