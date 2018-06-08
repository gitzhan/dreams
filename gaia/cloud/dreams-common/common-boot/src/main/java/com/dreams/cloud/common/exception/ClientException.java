/**
 * 
 */
package com.dreams.cloud.common.exception;

/**
 * 调用方异常。这类错误一般是servlet容器处理的，这里作为预留<br>
 * 当发生ClientException时，客户端返回的http code默认400
 * @author zhangxin13
 * @version 2017年11月2日下午7:19:12
 * @since 2017年11月2日
 */
public class ClientException extends AbstractException {
	private static final long serialVersionUID = 5214893309908224949L;
	public ClientException(ExceptionInfo info) {
		super(info);
	}
	public ClientException(ExceptionInfo info, Object body) {
		super(info, body);
	}
}
