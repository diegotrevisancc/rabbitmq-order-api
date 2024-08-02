package dev.trevisan.order.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItemRepository, UUID> {
}
