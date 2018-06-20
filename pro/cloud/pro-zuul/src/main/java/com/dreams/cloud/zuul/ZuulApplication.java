package com.dreams.cloud.zuul;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@RestController
@RefreshScope//需要再用到刷新配置的类上加上RefreshScope注解，否则无法访问到最新的配置，即：不在cloud bus刷新影响范围内
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Value("${token.filter.skipUrl}")
	private String[] skipTokenUrls;

	@GetMapping("/hi")
	public String hello() {
		System.out.println(JSON.toJSONString(skipTokenUrls));
		return "hi version is: "+JSON.toJSONString(skipTokenUrls);
	}
	@GetMapping("/hello")
	public String hello2() {
		System.out.println(JSON.toJSONString(skipTokenUrls));
		return "hello version is: "+JSON.toJSONString(skipTokenUrls);
	}
}
