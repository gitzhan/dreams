package com.dreams.cloud.base.common.service.user;

import com.dreams.cloud.base.common.config.BasicLogLevelConfig;
import com.dreams.cloud.base.common.service.user.fallback.AccountFallBack;
import com.dreams.cloud.common.structs.HttpResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: zyl
 * @version: v1.6.1
 * @date: 2018/6/21
 **/
@FeignClient(value = "pro-user", fallbackFactory = AccountFallBack.class, configuration = BasicLogLevelConfig.class)
public interface AccountClient {

	/**
	 * 注册
	 * @param account
	 * @return
	 */
	@PostMapping(path = "/account")
	HttpResult<String> register(@RequestBody String account);

	/**
	 * 根据账号名查账户
	 * @param username
	 * @return
	 */
	@GetMapping(path = "/account")
	HttpResult<String> getAccountIdByUsername(@RequestParam("username") String username);
}
