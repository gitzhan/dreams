/**
 * 
 */
package com.dreams.cloud.common.exception;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dreams.cloud.common.structs.HttpResult;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String FORMAT = "URI: {}, Method: {}, Parameter: {}, DebugMessage: {}";

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public HttpResult<String> handlerServerException(HttpServletRequest request, HttpServletResponse response,
            BusinessException e) {
        response.setStatus(200);// 总是返回200
        setResponse(response);
        logger.info("业务异常: code: {}, message: {}", e.getCode(), e.getMessage());
        String paramStr = parameterMap2String(request.getParameterMap());
        logger.warn(FORMAT, request.getRequestURI(), request.getMethod(), paramStr, e.getBody());
        return build(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public HttpResult<String> handlerServerException(HttpServletRequest request, HttpServletResponse response,
            ServerException e) {
        if (e.getCode() <= 500 || e.getCode() > 599) {// 没有设定 RFC 2616 规范所定义的错误码，则强制设定500
            response.setStatus(500);
            logger.warn("URI: {}, Method: {} 中返回了一个不严谨的错误码：{}", request.getRequestURI(), request.getMethod(),
                    e.getCode());
        } else {
            response.setStatus(e.getCode());
        }
        setResponse(response);
        String paramStr = parameterMap2String(request.getParameterMap());
        logger.error(FORMAT, request.getRequestURI(), request.getMethod(), paramStr, e.getBody());
        return build(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public HttpResult<String> handlerClientException(HttpServletRequest request, HttpServletResponse response,
            ClientException e) {
        if (e.getCode() <= 400 || e.getCode() > 499) {// 没有设定 RFC 2616 规范所定义的错误码，则强制设定400
            response.setStatus(400);
            logger.warn("URI: {}, Method: {} 中返回了一个不严谨的错误码：{}", request.getRequestURI(), request.getMethod(),
                    e.getCode());
        } else {
            response.setStatus(e.getCode());
        }
        setResponse(response);
        String paramStr = parameterMap2String(request.getParameterMap());
        logger.error(FORMAT, request.getRequestURI(), request.getMethod(), paramStr, e.getBody());
        return build(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResult<String> handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(500);// 所有未捕获的的异常
        setResponse(response);
        logger.error("发生未捕获异常! URI: {}, Method: {}, RemoteHost: {}, Error:{}", request.getRequestURI(), request.getMethod(),
                request.getRemoteHost(),e);
        return build(500, "发生未捕获异常!");
    }

    private String parameterMap2String(Map<String, String[]> map) {
        if (CollectionUtils.isEmpty(map)) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        for (Entry<String, String[]> entry : map.entrySet()) {
            sb.append(entry.getKey()).append('=').append(Arrays.toString(entry.getValue())).append(',');
        }
        sb.deleteCharAt(sb.length() - 1).append('}');
        return sb.toString();
    }

    private HttpResult<String> build(int code, String message) {
        HttpResult<String> r = new HttpResult<>();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    private void setResponse(final HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
    }

}
