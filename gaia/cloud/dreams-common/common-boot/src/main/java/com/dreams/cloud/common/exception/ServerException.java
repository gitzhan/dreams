/**
 * 
 */
package com.dreams.cloud.common.exception;

/**
 * 由服务器上的逻辑漏洞或者其他什么原因导致的无法由客户端重新提交来纠正的错误，
 * 比如服务器代码上写的不严谨在某个地方报了空指针异常，这种问题客户端无论提交多少次正确的参数都无法修正，
 * 因此是属于服务器内部错误。<br>
 * 当发生ServerException时，向客户端返回的http code为5xx。
 * @author zhangxin13
 * @version 2017年11月2日下午7:19:20
 * @since 2017年11月2日
 */
public class ServerException extends AbstractException {
	private static final long serialVersionUID = 3117351252348483331L;
	public ServerException(ExceptionInfo info) {
		super(info);
	}
	public ServerException(ExceptionInfo info, Object body) {
		super(info, body);
	}
}
