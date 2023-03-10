package com.example.RabbitMQ.controller;

import com.example.RabbitMQ.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name){
        Person person = new Person(1L, name);
        //only supports String, bytes e serializables objects
        rabbitTemplate.convertAndSend("Mobile", person);
        //direct
        rabbitTemplate.convertAndSend("Direct-exchange", "mobile", person);
        //fanout
        rabbitTemplate.convertAndSend("Fanout-exchange", "", person);
        //topic
        rabbitTemplate.convertAndSend("Topic-exchange", "tv.mobile.ac", person);
        return "Success";
    }
}
