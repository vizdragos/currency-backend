package com.currency.aop.socket;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Aspect
public class NotifyAspect {

	@Autowired
	private SimpMessagingTemplate template;

	private static final String WEBSOCKET_TOPIC = "/topic/notify";

	@Pointcut("@annotation(com.currency.aop.socket.NotifyClients)")
	public void notifyPointcut() {}

	@Pointcut("execution(* com.currency.web.**.*(..))")
	public void methodPointcut() {}

	@AfterReturning(pointcut = "methodPointcut() && notifyPointcut()", returning="returnValue")
	public Object notifyClients(JoinPoint joinPoint, Object returnValue) throws Throwable {
		template.convertAndSend(WEBSOCKET_TOPIC, returnValue);
		return returnValue;
	}

}