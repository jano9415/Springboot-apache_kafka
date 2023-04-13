package com.microservices.order_service.controller;

import com.microservices.base_domains.dto.Order;
import com.microservices.base_domains.dto.OrderEvent;
import com.microservices.order_service.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order actualOrder){

        actualOrder.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();

        orderEvent.setStatus("pending");
        orderEvent.setMessage("Status is pending");
        orderEvent.setOrder(actualOrder);

        orderProducer.sendMessage(orderEvent);

        return "Order sent to kafka";


    }
}
