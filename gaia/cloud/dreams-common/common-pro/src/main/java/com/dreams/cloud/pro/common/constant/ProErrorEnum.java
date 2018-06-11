package com.dreams.cloud.pro.common.constant;

import com.dreams.cloud.common.exception.ExceptionInfo;

public enum ProErrorEnum implements ExceptionInfo {
	TOKEN_ERROR(601,"token异常"),
	SAVE_EXCEPTION(599,"存储失败");

	private int code;
	private String message;
	private ProErrorEnum(int code, String message) {
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
