package com.dreams.cloud.usercenter.dto.token;

import lombok.Data;

@Data
public class UserTokenDTO {
	private String userId;
	private long expiresIn;

	public UserTokenDTO(String userId, long expiresIn) {
		super();
		this.userId = userId;
		this.expiresIn = expiresIn;
	}

	public UserTokenDTO() {
		super();
	}
}
