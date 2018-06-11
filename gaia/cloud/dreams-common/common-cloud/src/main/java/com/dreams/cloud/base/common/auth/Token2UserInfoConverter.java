package com.dreams.cloud.base.common.auth;

/**
 * 
 */
public interface Token2UserInfoConverter<T> {
	/**
	 * 通过token获取UserInfo
	 * @param token
	 * @return
	 */
	public T getUserInfo(String token);
}
