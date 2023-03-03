package com.estudorabbitmq.estudorabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(Publisher.class, args);

		//abrindo a conexão com o RabbitMQ
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

//		String message = "First message from RabbitMQ";
		String [] messages = {"First", "Second", "Third", "Fourth"};

		for (String message : messages) {
			//exchange - routingKey - props - body(bytes)
			channel.basicPublish("", "Queue-1", null, message.getBytes());
		}


		//Fechando a conexão
		channel.close();
		connection.close();
	}

}
