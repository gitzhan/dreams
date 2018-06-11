package com.dreams.cloud.pro.common.auth;

import java.util.Date;

import lombok.Data;

@Data
public class AccessUser {
	private String userId;
	private String userName;
	private String accountId;
	private String tenantId;
	private String tenantName;
	private String accessToken;
	private String refreshToken;
	private Integer expires;
	private String phoneNumber;
	private String name;
	private Integer gender;
	private Integer type;
	private Date loginTime;
	private String phoneVerify;


	/*public AccessUser(LoginResponseDTO loginResponseDTO, String flag, String navigateFlag, String phoneVerify,
			String areaName,Integer clientType) {
		super();
		this.userId = loginResponseDTO.getUserId();
		this.userName = loginResponseDTO.getUserName();
		this.accountId = loginResponseDTO.getAccountId();
		this.tenantId = loginResponseDTO.getTenantId();
		this.tenantName = loginResponseDTO.getTenantName();
		this.accessToken = loginResponseDTO.getAccessToken();
		this.refreshToken = loginResponseDTO.getRefreshToken();
		this.expires = loginResponseDTO.getExpires();
		this.phoneNumber = loginResponseDTO.getPhoneNumber();
		this.name = loginResponseDTO.getName();
		this.gender = loginResponseDTO.getGender();
		this.type = loginResponseDTO.getType();
		this.loginTime = loginResponseDTO.getLoginTime();
		this.menus = loginResponseDTO.getMenus();
		this.flag = flag;
		this.navigateFlag = navigateFlag;
		this.phoneVerify = phoneVerify;
		this.areaName = areaName;
		this.clientType=clientType;
	}*/

	public AccessUser() {
		super();
	}
}
