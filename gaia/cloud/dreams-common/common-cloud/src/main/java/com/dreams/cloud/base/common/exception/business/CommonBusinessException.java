package com.dreams.cloud.base.common.exception.business;

import com.dreams.cloud.common.exception.BusinessException;
import com.dreams.cloud.common.exception.ExceptionInfo;

/**
 * 通用异常类
 */
public class CommonBusinessException  extends BusinessException{
	private static final long serialVersionUID = -2356607257108093007L;
	public CommonBusinessException() {
		super(CommonBusinessCode.code);
	}

	/**
	 * 
	 * @param body 需要打印到日志的异常信息
	 */
	public CommonBusinessException(Object body) {
		super(CommonBusinessCode.code, body);
	}
	
	static class CommonBusinessCode implements ExceptionInfo{
		static final CommonBusinessCode code = new CommonBusinessCode();

		@Override
		public int getCode() {
			return 1;
		}

		@Override
		public String getMessage() {
			return null;
		}
		
	}

}
