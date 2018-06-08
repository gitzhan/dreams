package com.dreams.cloud.common.handler;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dreams.cloud.common.structs.HttpResult;

/**
 * <p></p>
 * @author  zhanyalei 
 * @version V1.0  
 * @date 2017年10月10日 上午8:57:32 
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {詹亚磊} 2017年10月10日
 * @since  
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

	private final HandlerMethodReturnValueHandler delegate;  
	public  ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate){  
	  this.delegate=delegate;  
	}  
	  
    public boolean supportsReturnType(MethodParameter returnType) {  
        return delegate.supportsReturnType(returnType);  
    }  
  
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {  
        if (returnValue!=null &&returnValue instanceof HttpResult) {
        	delegate.handleReturnValue(returnValue,returnType,mavContainer,webRequest);  
        }else {
        	HttpResult result = new HttpResult(returnValue); 
        	delegate.handleReturnValue(result,returnType,mavContainer,webRequest);  
		}
    }  
	
}
