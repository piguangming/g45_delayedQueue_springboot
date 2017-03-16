package com.git.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	@RabbitListener(queues="router1")
	@RabbitHandler
    public void process1(String context) {
        System.out.println("router1 : " + context);
    }
	
	@RabbitListener(queues="router2")
	@RabbitHandler
	public void process2(String context) {
		System.out.println("router2 : " + context);
	}
	
	@RabbitListener(queues="router3")
	@RabbitHandler
	public void process3(String context) {
		System.out.println("router3 : " + context);
	}
}
