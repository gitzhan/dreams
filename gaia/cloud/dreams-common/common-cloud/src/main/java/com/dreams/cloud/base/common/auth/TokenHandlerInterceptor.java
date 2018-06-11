package com.dreams.cloud.base.common.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 从header中获取token，并放置在当前线程的上下文中备用
 */
public class TokenHandlerInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(getClass());

	private TokenContext tokenContext;
	
	public TokenHandlerInterceptor(TokenContext tokenContext){
		this.tokenContext = tokenContext;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String auth = request.getHeader("Authorization");
		if(logger.isDebugEnabled()){
			logger.debug(auth);
		}
		if(auth!=null && auth.length()==43){ //6+1+36
			auth = auth.substring(7);
		}else{
			auth = null;
		}
		tokenContext.setToken(auth);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		tokenContext.setToken(null);
	}
	
	
}
