package com.dreams.cloud.usercenter.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreams.cloud.common.util.UUIDUtil;
import com.dreams.cloud.usercenter.dao.ProUserDao;
import com.dreams.cloud.usercenter.entity.ProUser;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private ProUserDao userDao;
	
	@GetMapping("/get")
	public ProUser getUser() {
		return userDao.loadById("111111");
	}
	
	@PostMapping("/regist")
	public void regist(@RequestBody ProUser user) {
		userDao.insert(user);
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