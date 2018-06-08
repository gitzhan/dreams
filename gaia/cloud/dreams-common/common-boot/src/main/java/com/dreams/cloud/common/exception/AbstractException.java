/**
 * 
 */
package com.dreams.cloud.common.exception;

/**
 * 最基础的异常类。
 * @author zhangxin13
 * @version 2017年11月2日下午7:20:46
 * @since 2017年11月2日
 */
abstract class AbstractException extends RuntimeException implements ExceptionInfo {
	private static final long serialVersionUID = 3289598099629324399L;
	private final int code;
	private final String message;
	private final transient Object body;
	public AbstractException(ExceptionInfo info){
		this.code = info.getCode();
		this.message = info.getMessage();
		this.body = null;
	}
	public AbstractException(ExceptionInfo info, Object body){
		this.code = info.getCode();
		this.message = info.getMessage();
		this.body = body;
	}
	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public int getCode() {
		return code;
	}
	public Object getBody() {
		return body;
	}
}
