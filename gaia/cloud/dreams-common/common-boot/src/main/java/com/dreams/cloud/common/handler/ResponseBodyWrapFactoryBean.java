package com.dreams.cloud.common.handler;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.dreams.cloud.common.util.CollectionUtil;

/**
 * <p></p>
 *
 * @author  zhanyalei 
 * @version V1.0  
 * @date 2017年10月10日 上午8:54:12 
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {詹亚磊} 2017年10月10日
 * @since  
 */
@Component
public class ResponseBodyWrapFactoryBean implements InitializingBean{
	
	@Autowired  
    private RequestMappingHandlerAdapter adapter;

	public void afterPropertiesSet() throws Exception {
		List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();  
        List<HandlerMethodReturnValueHandler> handlers = CollectionUtil.newArrayList(returnValueHandlers);  
        decorateHandlers(handlers);  
        adapter.setReturnValueHandlers(handlers); 
    }
	
	private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {  
        for (HandlerMethodReturnValueHandler handler : handlers) {  
            if (handler instanceof RequestResponseBodyMethodProcessor) {  
                //用自己的ResponseBody包装类替换掉框架的，达到返回Result的效果  
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);  
                int index = handlers.indexOf(handler);  
                handlers.set(index, decorator);  
                break;  
            }  
        }  
    } 
	
}
