package com.dreams.cloud.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.dreams.cloud.common.structs.HttpResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zyl
 * @version: v1.6.1
 * @date: 2018/6/20
 **/
@Component
public class ErrorFilter extends ZuulFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public String filterType() {
		return FilterConstants.ERROR_TYPE;
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		Throwable throwable = context.getThrowable();
		String errorMsg = throwable.getCause().getMessage();
		LOGGER.error("happened a error: "+ errorMsg +", throwable: "+throwable);
		try {
			HttpResult<Object> result = new HttpResult<>();
			result.setData("");
			result.setMessage(errorMsg);
			context.getResponse().getWriter().write(JSON.toJSONString(result));
		}catch (Exception e){
			LOGGER.error("ErrorFilter Exception:"+e);
		}
		return null;
	}
}
