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
    private AmqpTemplate rabbitTemplate;
	
	
	@RequestMapping("/send10")
	public BaseResponse send10(HttpServletRequest request){
		this.rabbitTemplate.convertAndSend("10S_Delay", "订单_"+new Date().getTime());
		return BaseResponse.SUCCESSFUL();
	}
	@RequestMapping("/send20")
	public BaseResponse send20(HttpServletRequest request){
		this.rabbitTemplate.convertAndSend("20S_Delay", "短信_"+new Date().getTime());
		return BaseResponse.SUCCESSFUL();
	}
	@RequestMapping("/send30")
	public BaseResponse send30(HttpServletRequest request){
		this.rabbitTemplate.convertAndSend("30S_Delay", "推送_"+new Date().getTime());
		return BaseResponse.SUCCESSFUL();
	}
}
