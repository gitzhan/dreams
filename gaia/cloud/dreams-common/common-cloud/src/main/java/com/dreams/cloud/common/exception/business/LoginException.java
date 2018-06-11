package com.dreams.cloud.common.exception.business;

import com.dreams.cloud.common.constant.GeneralErrorEnum;
import com.dreams.cloud.common.exception.BusinessException;

public class LoginException extends BusinessException {
	private static final long serialVersionUID = 7559193386875954697L;

	public LoginException(Object body) {
		super(GeneralErrorEnum.LOGIN_ERROR,body);
	}

}
