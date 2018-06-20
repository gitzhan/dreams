package com.dreams.cloud.usercenter.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreams.cloud.base.common.auth.TokenRequired;
import com.dreams.cloud.base.common.auth.UserInfo;
import com.dreams.cloud.base.common.dto.user.LoginRequestDTO;
import com.dreams.cloud.base.common.dto.user.LoginResponseDTO;
import com.dreams.cloud.base.common.exception.business.LoginException;
import com.dreams.cloud.common.util.UUIDUtil;
import com.dreams.cloud.pro.common.auth.AccessUser;
import com.dreams.cloud.usercenter.dao.ProUserDao;
import com.dreams.cloud.usercenter.entity.ProUser;
import com.dreams.cloud.usercenter.service.user.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private ProUserDao userDao;
	@Resource
	private UserService userService;
	
	@GetMapping("/get")
	public ProUser getUser() {
		return userDao.loadById("111111");
	}

	@PostMapping("/regist")
	public void regist(@RequestBody ProUser user) {
		userDao.insert(user);
	}
	
	@PostMapping("/login")
	@TokenRequired
	public LoginResponseDTO login(@UserInfo AccessUser user, @RequestBody LoginRequestDTO loginRequest) {
		if (null==loginRequest) {
			throw new LoginException("参数错误");
		}
		return userService.login(loginRequest);
	}
	
	@PostMapping("/logout")
	@TokenRequired
	public void logout(@UserInfo AccessUser user) {
		userService.logout();
	}
	
	@GetMapping("/regist/test")
	public void regist() {
		ProUser user = new ProUser();
		user.setUserId(UUIDUtil.getUUID());
		user.setAccount("aaaaaa");
		user.setCid("cidcid");
		user.setEmail("1@1.com");
		user.setGender(0);
		user.setName("userTest");
//		user.setPassword(EncryptHandler.aesDecode("abcd1234"));
		user.setPassword("abcd1234");
		user.setPhone("12345678901");
		user.setType(0);
//		user.setToken(TokenUtil.generateToken(user.getUserId()));
		user.setToken(user.getUserId()+"@"+System.currentTimeMillis());
		userDao.insert(user);
	}

}
