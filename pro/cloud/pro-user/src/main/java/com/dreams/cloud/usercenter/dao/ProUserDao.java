package com.dreams.cloud.usercenter.dao;

import org.apache.ibatis.annotations.Param;

import com.dreams.cloud.usercenter.entity.ProUser;

public interface ProUserDao {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public ProUser loadById(@Param("userId")String userId);
	
	/**
	 * 插入信息
	 * @param proUser 
	 * @return
	 */
	int insert(ProUser proUser);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(@Param("userId")String userId);
	
}
