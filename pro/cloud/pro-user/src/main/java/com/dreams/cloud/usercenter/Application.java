package com.dreams.cloud.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableEurekaClient 用EnableDiscoveryClient代替
@EnableDiscoveryClient
@EnableFeignClients
//@EnableHystrix //开启断路器注解 http://host:port/hystrix.stream
//@EnableHystrixDashboard //开启断路器监控 http://host:port/hystrix
@RestController
//@ComponentScan
//@ServletComponentScan
@MapperScan("com.dreams.cloud.usercenter.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Value("${server.port}")
    String port;
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
}
