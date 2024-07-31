package dev.trevisan.order.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.trevisan.order.api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private Status status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}
