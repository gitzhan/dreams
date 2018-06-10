package com.dreams.cloud.usercenter.security;

import org.springframework.stereotype.Component;

@Component
public interface ISecurityHandler {

	/**
	 * 加密
	 * @param content
	 * @return
	 */
	String AESEncode(String content);
	
	/**
	 * 解密
	 * @param content
	 * @return
	 */
	String AESDncode(String content);
	
}
