/**
 * @ProjectName: 智能建筑
 * @Copyright: 2012 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016年9月26日 上午10:53:05
 * @Description: 本内容仅限于杭州海康威视数字技术系统公司内部使用，禁止转发.
 */
package com.dreams.cloud.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {
    
    /*private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public HttpResult baseExceptionHandler(HttpServletRequest req, BaseException e) throws Exception {
        HttpResult ret = new HttpResult();
        ret.setCode(e.getCode());
        ret.setMessage(e.getMsg());
        return ret;
    }
    
    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    public HttpResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("", e);
        HttpResult ret = new HttpResult();
        ret.setCode(-1);
        ret.setMessage("异常情况！");
        return ret;
    }*/
}