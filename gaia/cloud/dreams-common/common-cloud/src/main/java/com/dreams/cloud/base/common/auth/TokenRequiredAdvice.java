package com.dreams.cloud.base.common.auth;

import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import com.dreams.cloud.base.common.exception.client.TokenNotExistException;

@Aspect
@Order(value=Ordered.HIGHEST_PRECEDENCE+2)
public class TokenRequiredAdvice {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TokenContext tokenContext;
	@Autowired
	private Token2UserInfoConverter<?> converter;
	
	/**
	 * 切面声明
	 */
	@Pointcut("@annotation(com.dreams.cloud.base.common.auth.TokenRequired)")
    public void getTokenRequired() {}
	
	/**
	 * 根据token获取用户信息。如果token为空，则报客户端错误。
	 * @param point
	 * @throws Throwable 
	 */
	@Around("getTokenRequired()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		//先检查token是否存在
		if(StringUtils.isEmpty(tokenContext.getToken())){
			throw new TokenNotExistException();
		}
		Parameter userArg = null;
		int i = 0;
		for(Parameter param:((MethodSignature)joinPoint.getSignature()).getMethod().getParameters()){
			UserInfo info = param.getAnnotation(UserInfo.class);
			if(null!=info){
				userArg = param;
				break;
			}
			i++;
		}
		
		if(userArg==null){
			logger.warn("该方法声明了 @TokenRequired 却没有在参数中添加 @UserInfo，请确保注解使用正确！");
			return joinPoint.proceed();
		}
		
		Object[] args = joinPoint.getArgs();
		//注入用户信息的变量
		args[i] = converter.getUserInfo(tokenContext.getToken());
		return joinPoint.proceed(args);
	}
	
	
//	@Pointcut("within(com.hikvision.building.cloud.eden.common.service..*+) && !@within(org.springframework.stereotype.Component)")// 
//  public void getClient() {
//  }
//	
//	/**
//	 * 自动给feign的客户端请求填充token，如果不需要，则可以不设置@RequestHeader("token") String token
//	 * @param joinPoint
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("getClient()")
//	public Object client(ProceedingJoinPoint joinPoint) throws Throwable{
//		Object[] args = joinPoint.getArgs();
//		Parameter[] params = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameters();
//		for(int i = 0; i < args.length; i++){
//			Parameter param = params[i];
//			RequestHeader header =  param.getAnnotation(RequestHeader.class);
//			if(null!=header && "Authorization".equals(header.value())){
//				args[i] = "Bearer "+tokenContext.getToken();
//				break;
//			}
//		}
//		
//		return joinPoint.proceed(args);
//	}
	
}
