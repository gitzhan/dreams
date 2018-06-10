package com.dreams.cloud.common.util;

import java.util.UUID;

public class UUIDUtil {
	
	private UUIDUtil() {}
	
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("\\-", "").toLowerCase(); 
	}
	
}
