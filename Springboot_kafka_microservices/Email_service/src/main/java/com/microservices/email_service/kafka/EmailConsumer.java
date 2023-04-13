package com.microservices.email_service.kafka;

import com.microservices.base_domains.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.name}" , groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){
        System.out.println("Order event is received in the email service: " + orderEvent.toString());

    }
}
