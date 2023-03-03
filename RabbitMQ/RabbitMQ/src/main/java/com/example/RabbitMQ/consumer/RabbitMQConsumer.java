package com.example.RabbitMQ.consumer;

import com.example.RabbitMQ.model.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "Mobile")
    public void getMessage(Person person){
        System.out.println(person.getName());
    }

}
