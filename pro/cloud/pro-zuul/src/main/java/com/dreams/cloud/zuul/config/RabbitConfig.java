package com.dreams.cloud.zuul.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	@Autowired
	private RabbitProperties rabbitProperties;

	@Bean
	@Qualifier
	public ConnectionFactory connectionFactory() {
		System.out.println("==========================================");
		System.out.println(JSON.toJSONString(rabbitProperties.getHost()+"--"+rabbitProperties.getPort()));
		System.out.println(JSON.toJSONString(rabbitProperties.getUsername()+"--"+rabbitProperties.getPassword()));
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(rabbitProperties.getHost() + ":" + rabbitProperties.getPort());
		//这里可以考虑加解密处理
		connectionFactory.setUsername(rabbitProperties.getUsername());
		connectionFactory.setPassword(rabbitProperties.getPassword());
		connectionFactory.setVirtualHost("/");
		connectionFactory.setPublisherConfirms(true); // 必须要设置
		return connectionFactory;
	}
}
