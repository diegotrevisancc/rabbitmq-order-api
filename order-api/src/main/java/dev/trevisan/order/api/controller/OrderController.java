package dev.trevisan.order.api.controller;

import dev.trevisan.order.api.entity.Order;
import dev.trevisan.order.api.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Orders", description="Resource to build new orders")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create new order", description = "Contains all operations needed to create new orders"
    , responses = @ApiResponse(responseCode = "201", description = "Resource created with success",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    )
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        logger.info("Order received: {}", order.toString());
        order = orderService.queueOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order); // 201
    }
}
