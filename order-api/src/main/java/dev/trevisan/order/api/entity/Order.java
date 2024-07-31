package dev.trevisan.order.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private UUID id = UUID.randomUUID();
    private String customer;
    private List<OrderItem> items;
    private Double sum;
    private String customerEmail;
}
