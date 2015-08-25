package com.wisedu.amp.fserver.auth.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wisedu.amp.fserver.auth.dao.IAccessTokenDao;
import com.wisedu.amp.fserver.auth.dao.IWhiteListDao;
import com.wisedu.amp.fserver.auth.entity.AccessToken;
import com.wisedu.amp.fserver.auth.service.IAuthCacheService;

/**
 * 认证缓存，使用redis实现
 * 
 * @author zengxianping
 *
 */
@Service
public class RedisAuthCache implements IAuthCacheService {
	/**
	 * 访问令牌键前缀
	 */
	public static final String ACCESS_TOKEN_KEY_PREFIX = KEY_PREFIX + "token.";
	/**
	 * ip白名单键前缀
	 */
	public static final String WHITE_lIST_KEY_PREFIX = KEY_PREFIX + "ip.";
	@Autowired
	private IAccessTokenDao accessTokenDao;
	@Autowired
	private IWhiteListDao ipWhiteListManager;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void init() {
		// 访问令牌
		List<AccessToken> accessTokens = accessTokenDao.findAll();
		if (accessTokens != null && accessTokens.size() > 0) {
			for (AccessToken accessToken : accessTokens) {
				redisTemplate.opsForValue().set(
						ACCESS_TOKEN_KEY_PREFIX + accessToken.getAppId(),
						accessToken.getToken());
			}
		}
		// ip白名单
		Map<String, Set<String>> ipWhiteLists = ipWhiteListManager.findAll2();
		if (ipWhiteLists != null && ipWhiteLists.size() > 0) {
			for (Entry<String, Set<String>> entry : ipWhiteLists.entrySet()) {
				String key = WHITE_lIST_KEY_PREFIX + entry.getKey();
				Set<String> ipWhiteList = entry.getValue();
				redisTemplate.opsForSet().add(key,
						ipWhiteList.toArray(new String[ipWhiteList.size()]));
			}
		}
	}

	/**
	 * 根据应用编号，获取ip白名单列表
	 * 
	 * @param appId
	 *            应用编号
	 * @return ip白名单列表
	 */
	public Set<String> getIPWhiteList(String appId) {
		return redisTemplate.opsForSet().members(WHITE_lIST_KEY_PREFIX + appId);
	}

	/**
	 * @param appId
	 * @param ipWhiteList
	 */
	public void setIPWhiteList(String appId, Set<String> ipWhiteList) {
		redisTemplate.opsForSet().add(WHITE_lIST_KEY_PREFIX + appId,
				ipWhiteList.toArray(new String[ipWhiteList.size()]));
	}

	/**
	 * @param appId
	 * @param ip
	 */
	public void setIPWhiteList(String appId, String ip) {
		redisTemplate.opsForSet().add(WHITE_lIST_KEY_PREFIX + appId, ip);
	}

	/**
	 * @param appId
	 * @return
	 */
	public String getAccessToken(String appId) {
		return redisTemplate.opsForValue().get(ACCESS_TOKEN_KEY_PREFIX + appId);
	}

	/**
	 * @param appId
	 * @param accessToken
	 */
	public void setAccessToken(String appId, String accessToken) {
		redisTemplate.opsForValue().set(ACCESS_TOKEN_KEY_PREFIX + appId, accessToken);
	}

	public void delIPWhiteList(String appId) {
		redisTemplate.delete(WHITE_lIST_KEY_PREFIX + appId);

	}

	public void delIPWhiteList(String appId, String ip) {
		redisTemplate.opsForSet().remove(WHITE_lIST_KEY_PREFIX + appId, ip);
	}

	public boolean containsIp(String appId, String ip) {
		if (StringUtils.isEmpty(appId)) {
			return false;
		}
		return redisTemplate.opsForSet().isMember(WHITE_lIST_KEY_PREFIX + appId, ip);
	}

	public boolean containsAccessToken(String appId, String accessToken) {
		if (StringUtils.isEmpty(appId)) {
			return false;
		}
		return redisTemplate.opsForValue().get(ACCESS_TOKEN_KEY_PREFIX + appId)
				.equals(accessToken);
	}

	public void delAccessToken(String appId) {
		redisTemplate.delete(ACCESS_TOKEN_KEY_PREFIX + appId);
	}

	public void reset() {
		clear();
		init();
	}

	public void clear() {
		redisTemplate.delete(KEY_PREFIX + "*");
	}
}
