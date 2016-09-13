package com.whl.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whl.App;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class Send {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Scheduled(fixedDelay = 3000)
	public void send() {
		System.out.println("=========================");
		String string = "Hello RabbitmQ";
		rabbitTemplate.convertAndSend(string);
	}

	@Bean
	Queue queue() {
		return new Queue("springboot-queue", true);
	}

}
