package com.dreams.cloud.usercenter.service.user;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dreams.cloud.base.common.dto.user.LoginRequestDTO;
import com.dreams.cloud.base.common.dto.user.LoginResponseDTO;
import com.dreams.cloud.base.common.exception.business.LoginException;
import com.dreams.cloud.usercenter.dao.ProUserDao;
import com.dreams.cloud.usercenter.entity.ProUser;
import com.dreams.cloud.usercenter.util.TokenUtil;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Resource
	private ProUserDao userDao;
	
	public LoginResponseDTO login(LoginRequestDTO loginRequest) {
		ProUser user = userDao.getByAccount(loginRequest.getAccount(), loginRequest.getPassword());
		if (null==user) {
			user = userDao.getByPhone(loginRequest.getAccount(), loginRequest.getPassword());
		}
		if (null==user) {
			throw new LoginException(null);
		}
		LOGGER.info("login user:{}",user);
		String userId = user.getUserId();
		String token = TokenUtil.generateToken(user.getUserId());
		userDao.updateToken(userId, token);
		LoginResponseDTO loginResponse = new LoginResponseDTO();
		loginResponse.setUserId(userId);
		loginResponse.setAccount(user.getAccount());
		loginResponse.setPhone(user.getPhone());
		loginResponse.setGender(user.getGender());
		loginResponse.setLoginTime(new Date());
		loginResponse.setName(user.getName());
		loginResponse.setAccessToken(token);
		loginResponse.setExpires(TokenUtil.getExpires());
		return loginResponse;
	}
	
	public void logout() {
		
	}
}
