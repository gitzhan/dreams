/**
 * 
 */
package com.dreams.cloud.common.exception;

/**
 * 业务异常。比如输入错误的数据导致业务失败，逻辑进入到非主线分支，需要拒绝服务，让客户端提交正确的参数即可恢复。
 * 因此当遇到这种异常的时候，其实业务逻辑是执行成功的，只不过进入了非主线分支而已<br>
 * 当发生BusinessException时，客户端返回的http code总是200
 * @author zhangxin13
 * @version 2017年11月7日上午9:40:35
 * @since 2017年11月7日
 */
public class BusinessException extends AbstractException {
	private static final long serialVersionUID = -4853537210488828688L;
	public BusinessException(ExceptionInfo info) {
		super(info);
	}
	public BusinessException(ExceptionInfo info, Object body) {
		super(info, body);
	}
}
