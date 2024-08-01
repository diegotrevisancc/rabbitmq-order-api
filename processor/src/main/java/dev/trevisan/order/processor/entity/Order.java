package dev.trevisan.order.processor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.trevisan.order.processor.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order")
public class Order {
    @Id
    private UUID id = UUID.randomUUID();

    private String customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @Column(name = "total_amount")
    private Double sum;

    @Column(name = "customer_email")
    private String customerEmail;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PROCESSING;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now();
}
