package com.dreams.cloud.usercenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dreams.cloud.base.common.auth.TokenContext;
import com.dreams.cloud.base.common.auth.TokenHandlerInterceptor;
import com.dreams.cloud.base.common.auth.TokenRequiredAdvice;
import com.dreams.cloud.pro.common.auth.AccessUserConverter;

@Configuration
public class TokenConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration ir = registry.addInterceptor(new TokenHandlerInterceptor(getTokenContext()));
		ir.addPathPatterns("/**");
	}

	@Bean
	public TokenRequiredAdvice getTokenRequiredAdvice() {
		return new TokenRequiredAdvice();
	}

	@Bean
	public TokenContext getTokenContext() {
		return new TokenContext();
	}

	@Bean
	public AccessUserConverter getAccessUserConverter() {
		return new AccessUserConverter();
	}

//	@Bean
//	public RequestInterceptor getRequestConvertInterceptor() {
//		return new RequestConvertInterceptor();
//	}
}
