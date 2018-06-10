package com.dreams.cloud.usercenter.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * userId
	 */
	private String userId;
		
	/**
	 * 用户账号
	 */
	private String account;
		
	/**
	 * 密码
	 */
	private String password;
		
	/**
	 * 用户类型(0:自定义注册用户 1:微信 2:其他...)
	 */
	private Integer type;
		
	/**
	 * 手机号
	 */
	private String phone;
		
	/**
	 * 昵称
	 */
	private String name;
		
	/**
	 * 性别(0:男 1:女)
	 */
	private Integer gender;
		
	/**
	 * 邮箱
	 */
	private String email;
		
	/**
	 * 头像
	 */
	private String photo;
		
	/**
	 * token
	 */
	private String token;
		
	/**
	 * cid(推送)
	 */
	private String cid;
		
	/**
	 * 是否删除
	 */
	private Integer isDelete;
}