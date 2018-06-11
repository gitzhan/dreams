package com.dreams.cloud.pro.common.auth;

import com.dreams.cloud.base.common.auth.Token2UserInfoConverter;

public class AccessUserConverter implements Token2UserInfoConverter<AccessUser> {

//	@Resource
//	private JedisUtil jedisUtil;
//	@Autowired
//	private HomeClient homeClient;

	@Override
	public AccessUser getUserInfo(String token) {
//		AccessUser accessUser = jedisUtil.get(CCRedisConstant.USER_SESSION_PREFIX + token, AccessUser.class);
//
//		return accessUser == null ? getSessionUser(token) : accessUser;
		return new AccessUser();
	}

	/*private AccessUser getSessionUser(String token) {
		HttpResult<SessionUserDTO> sessionUserResult = homeClient
				.getSession(ServiceTypeEnum.SERVICE_TYPE_RETAIL.getType());

		AccessUser accessUser = new AccessUser();

		if (sessionUserResult != null && sessionUserResult.isSuccess() && sessionUserResult.getData() != null) {
			SessionUserDTO sessionUser = sessionUserResult.getData();

			accessUser.setAccessToken(sessionUser.getAccessToken());
			accessUser.setAccountId(sessionUser.getAccountId());
			accessUser.setClientType(sessionUser.getClientType());
			accessUser.setLoginTime(sessionUser.getLoginTime());
			accessUser.setMenus(sessionUser.getMenus());
			accessUser.setPhoneNumber(sessionUser.getPhoneNumber());
			accessUser.setRefreshToken(sessionUser.getRefreshToken());
			accessUser.setTenantId(sessionUser.getTenantId());
			accessUser.setTenantName(sessionUser.getTenantName());
			accessUser.setType(sessionUser.getType());
			accessUser.setUserId(sessionUser.getUserId());
			accessUser.setUserName(sessionUser.getUserName());

			int expires = sessionUser.getExpires()
					- (int) ((new Date().getTime() - sessionUser.getLoginTime().getTime()) / 1000);

			accessUser.setExpires(expires);

			jedisUtil.setObjectEx(CCRedisConstant.USER_SESSION_PREFIX + sessionUser.getAccessToken(),
					accessUser.getExpires(), accessUser);

			jedisUtil.setEx(CCRedisConstant.USER_TOKEN_PREFIX + sessionUser.getUserId(), accessUser.getExpires(),
					sessionUser.getAccessToken());
		}

		return accessUser;
	}
*/
}
