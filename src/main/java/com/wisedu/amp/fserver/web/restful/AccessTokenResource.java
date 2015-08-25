/**
 * 
 */
package com.wisedu.amp.fserver.web.restful;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wisedu.amp.fserver.auth.dao.impl.AccessTokenDao;
import com.wisedu.amp.fserver.auth.service.IAuthCacheService;
import com.wisedu.amp.fserver.exception.AccessTokenAlreadyGeneratedException;

/**
 * 访问令牌生成器，提供给应用管理平台使用，当一个应用需要使用流程时，必须申请一个accessToken
 * 
 * @author zengxianping
 *
 */
@RestController
public class AccessTokenResource {
	@Autowired
	private AccessTokenDao accessTokenDao;
	@Autowired
	private IAuthCacheService authCacheService;

	/**
	 * 为应用生成访问令牌
	 * 
	 * @param appId
	 *            应用编号
	 * @return 访问令牌
	 */
	@RequestMapping(value = "/accessTokens/", method = RequestMethod.POST, produces = "application/json")
	public String genAccessToken(String appId) {
		String accessToken = null;
		if (StringUtils.isEmpty(appId)) {
			throw new IllegalArgumentException("应用编号不能为空！");
		}
		if (StringUtils.isNotEmpty(authCacheService.getAccessToken(appId))) {
			throw new AccessTokenAlreadyGeneratedException("已为该应用生成过访问令牌！");
		}
		accessToken = accessTokenDao.genAccessToken(appId);
		// 更新访问令牌缓存
		authCacheService.setAccessToken(appId, accessToken);
		return accessToken;
	}
}
