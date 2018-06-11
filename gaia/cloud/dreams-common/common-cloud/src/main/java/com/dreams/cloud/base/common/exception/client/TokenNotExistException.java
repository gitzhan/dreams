package com.dreams.cloud.base.common.exception.client;

import com.dreams.cloud.base.common.constant.GeneralErrorEnum;
import com.dreams.cloud.common.exception.ClientException;

/**
 * Token不存在异常。由于方法声明了@TokenRequired而客户端请求未带有token时，抛出的异常
 */
public class TokenNotExistException extends ClientException{
	private static final long serialVersionUID = -4634200214914219442L;

	public TokenNotExistException() {
		super(GeneralErrorEnum.TOKEN_NOT_EXIST);
	}

}
