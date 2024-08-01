package dev.trevisan.order.processor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id

    private UUID id = UUID.randomUUID();

    @ManyToOne
    private Product product;

    private Integer quantity;

    @ManyToOne
    private Order order;
}
