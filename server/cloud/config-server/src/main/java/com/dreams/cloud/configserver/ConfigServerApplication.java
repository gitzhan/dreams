package com.dreams.cloud.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(ConfigServerApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ConfigServerApplication.class, args);
		Environment env = context.getEnvironment();
		if (env == null) {
			return;
		}
		String[] activeProfiles = env.getActiveProfiles();
		for (String profile : activeProfiles) {
			LOGGER.info("集群当前使用的配置文件为:{}", profile);
		}
	}
}
