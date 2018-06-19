package com.dreams.cloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dreams.cloud.common.structs.HttpResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TokenFilter extends ZuulFilter{
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);
	
	@Value("${token.filter.skipUrl}")
	private String[] skipTokenUrls;
	
	/**
	    filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： 
		pre：路由之前
		routing：路由之时
		post： 路由之后
		error：发送错误调用
		filterOrder：过滤的顺序
		shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
		run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
	 */
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUrl = request.getRequestURL().toString();
        LOGGER.info("method:{},url:{}", request.getMethod(), requestUrl);
        if (LOGGER.isInfoEnabled()) {
        	LOGGER.info(String.format("%s >>> %s", request.getMethod(), requestUrl));
		}
        if (null!=skipTokenUrls && skipTokenUrls.length>0) {
        	int index = requestUrl.lastIndexOf('/')+1;
			String targetUrl = requestUrl.substring(index);
			for (String url : skipTokenUrls) {
				if (url.equals(targetUrl)) {
					String accessToken = request.getParameter("accessToken");
//					String accessToken = request.getHeader("accessToken");
					if(StringUtils.isBlank(accessToken)) {
						LOGGER.warn("accessToken is empty");
						ctx.setSendZuulResponse(false);
						ctx.setResponseStatusCode(401);
						try {
							HttpResult<Object> result = new HttpResult<>();
							result.setData("accessToken required!");
							result.setMessage("accessToken is empty");
							ctx.getResponse().getWriter().write(JSON.toJSONString(result));
						}catch (Exception e){
							LOGGER.error("TokenFilter Exception:"+e);
						}
					}
					break;
				}
			}
		}
        return null;
	}
}
