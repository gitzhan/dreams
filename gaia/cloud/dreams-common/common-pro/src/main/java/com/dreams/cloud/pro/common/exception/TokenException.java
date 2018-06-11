package com.dreams.cloud.pro.common.exception;

import com.dreams.cloud.common.exception.BusinessException;
import com.dreams.cloud.pro.common.constant.ProErrorEnum;

public class TokenException extends BusinessException {
	private static final long serialVersionUID = 3035354592581887920L;

	public TokenException(Object body) {
		super(ProErrorEnum.TOKEN_ERROR, body);
	}
}
