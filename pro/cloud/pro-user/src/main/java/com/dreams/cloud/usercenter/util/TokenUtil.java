package com.dreams.cloud.usercenter.util;

import org.apache.commons.lang.StringUtils;

import com.dreams.cloud.usercenter.dto.token.UserTokenDTO;
import com.dreams.cloud.usercenter.security.EncryptHandler;

public class TokenUtil {
	private TokenUtil() {}
	
	private static final String TAG = "@"; 
	
	/**
	 * 单位:ms
	 */
	private static final long EXPIRES = 7*24*60*60*1000L; 
	
	public static String generateToken(String userId) {
		String currentTime = String.valueOf(System.currentTimeMillis());
		//暂时token使用用户ID+时间戳
		return EncryptHandler.tokenAESEncode(userId + TAG + currentTime);
	}
	
	public static UserTokenDTO doToken(String token) {
		if (StringUtils.isBlank(token)) {
			throw new RuntimeException("Token为空");
//			return null;
		}
		String decodeToken = EncryptHandler.tokenAESDecode(token);
		int index = decodeToken.lastIndexOf(TAG);
		String userId = decodeToken.substring(0, index);
		long expiresIn = Long.parseLong(decodeToken.substring(index+1));
		return new UserTokenDTO(userId, expiresIn);
	}
	
	public static long getExpires() {
		return System.currentTimeMillis()+EXPIRES;
	}
	
}
