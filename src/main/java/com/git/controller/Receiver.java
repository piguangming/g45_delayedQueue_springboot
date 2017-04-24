package com.git.controller;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	@RabbitListener(queues="10S_do")
	@RabbitHandler
	public void process4(String context) {
		System.out.println(new Date()+"10S_do 判断订单是否已经支付,没有支付就设置为无效 : " + context);
	}
	@RabbitListener(queues="20S_do")
	@RabbitHandler
	public void process5(String context) {
		System.out.println(new Date()+"20S_do : " + context);
	}
	@RabbitListener(queues="30S_do")
	@RabbitHandler
	public void process6(String context) {
		System.out.println(new Date()+"30S_do : " + context);
	}
}
