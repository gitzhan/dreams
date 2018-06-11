package com.dreams.cloud.base.common.dto.user;

import lombok.Data;

@Data
public class LoginRequestDTO {
	private String account;
	private String password;
	/**
	 * 0手机号密码登录,1手机号验证码登录
	 */
//    private Integer loginType;
}
