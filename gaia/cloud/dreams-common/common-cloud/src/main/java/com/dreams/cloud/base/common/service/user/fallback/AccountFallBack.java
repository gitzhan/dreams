package com.dreams.cloud.base.common.service.user.fallback;

import com.dreams.cloud.base.common.service.user.AccountClient;
import com.dreams.cloud.common.structs.HttpResult;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zyl
 * @version: v1.6.1
 * @date: 2018/6/21
 **/
@Component
public class AccountFallBack implements AccountClient {

	@Override
	public HttpResult<String> register(String account) {
		return null;
	}

	@Override
	public HttpResult<String> getAccountIdByUsername(String username) {
		return null;
	}
}
