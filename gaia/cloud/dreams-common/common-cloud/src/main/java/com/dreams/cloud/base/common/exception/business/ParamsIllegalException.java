package com.dreams.cloud.base.common.exception.business;

import com.dreams.cloud.base.common.constant.GeneralErrorEnum;
import com.dreams.cloud.common.exception.BusinessException;

/**
 * 请求参数不合法，如格式错误
 */
public class ParamsIllegalException extends BusinessException {
	private static final long serialVersionUID = 7559193386875954697L;

	public ParamsIllegalException(Object body) {
		super(GeneralErrorEnum.PARAMS_ILLEGAL,body);
	}

}
