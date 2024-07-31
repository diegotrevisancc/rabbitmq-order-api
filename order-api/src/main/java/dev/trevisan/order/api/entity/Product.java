package dev.trevisan.order.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private UUID id = UUID.randomUUID();
    private String name;
    private Double value;
}
