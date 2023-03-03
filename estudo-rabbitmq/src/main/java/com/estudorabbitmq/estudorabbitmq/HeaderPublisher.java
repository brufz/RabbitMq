package com.estudorabbitmq.estudorabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeaderPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "This is message for mobile and tv";
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");

        AMQP.BasicProperties br = new AMQP.BasicProperties();
        br = br.builder().headers(headersMap).build();

        channel.basicPublish("Headers-exchange", "", br, message.getBytes());

        channel.close();
        connection.close();
    }
}
