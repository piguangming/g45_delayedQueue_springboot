package com.git.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.domain.BaseResponse;
import com.git.service.DemoService;

@RestController
public class DemoController {
	@Autowired
	private DemoService demoService;
	
	@Autowired
    private AmqpTemplate rabbitTemplate;
	
	
	@RequestMapping("/send")
	public BaseResponse send(HttpServletRequest request,String queue,String context){
		this.rabbitTemplate.convertAndSend(queue, context+"_"+new Date().getTime());
		return BaseResponse.SUCCESSFUL();
	}
	@RequestMapping("/sendToExchange")
	public BaseResponse send(HttpServletRequest request,String exchange,String routeKey,String context){
		this.rabbitTemplate.convertAndSend(exchange, routeKey, context);
		return BaseResponse.SUCCESSFUL();
	}
}
