package com.whl.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "springboot-queue")
public class Recv {

	@RabbitHandler
	public void process(@Payload Message message) {
		System.out.println("__________" + message.getBody().toString() + "__________");
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
