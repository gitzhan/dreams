package com.dreams.cloud.base.common.constant;

import com.dreams.cloud.common.exception.ExceptionInfo;

public enum GeneralErrorEnum implements ExceptionInfo{
	/**
	 * 600001
	 */
	LOGIN_ERROR(600001, "账号或密码错误"),
	
	/**
	 * 49900,"请求参数不合法，如格式错误"
	 */
	PARAMS_ILLEGAL(49900,"请求参数不合法，如格式错误"),
	/**
	 * 401,"请求未带有Token"
	 */
	TOKEN_NOT_EXIST(401,"请求未带有Token");
	
	private int code;
	private String message;
	private GeneralErrorEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	@Override
	public int getCode() {
		return code;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
