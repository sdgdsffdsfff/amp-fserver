package com.wisedu.amp.fserver.auth.service;

import java.util.Set;

/**
 * 认证缓存接口，缓存ip白名单和访问令牌
 * 
 * @author tives
 *
 */
public interface IAuthCacheService {
	
	/**
	 * 缓存前缀名
	 */
	String KEY_PREFIX = "amp_flow_center:";

	/**
	 * 在Spring容器初始化的时候，从数据库中加载所有的IP白名单和访问令牌集合，置于缓存中
	 */
	public void init();
	
	/**
	 * 手动更改数据库时，重置缓存
	 */
	public void reset();
	
	/**
	 * 清空缓存中保存的认证信息
	 */
	public void clear();

	/**
	 * 根据应用编号，获取ip白名单列表
	 * 
	 * @param appId
	 *            应用编号
	 * @return ip白名单列表
	 */
	public Set<String> getIPWhiteList(String appId);

	/**
	 * @param appId
	 * @param ipWhiteList
	 */
	public void setIPWhiteList(String appId, Set<String> ipWhiteList);

	/**
	 * @param appId
	 * @param ip
	 */
	public void setIPWhiteList(String appId, String ip);
	
	/**
	 * @param appId
	 * @param ip
	 * @return
	 */
	public boolean containsIp(String appId, String ip);
	
	/**
	 * @param appId
	 */
	public void delIPWhiteList(String appId);
	
	/**
	 * @param appId
	 * @param ip
	 */
	public void delIPWhiteList(String appId,String ip);

	/**
	 * @param appId
	 * @return
	 */
	public String getAccessToken(String appId);

	/**
	 * @param appId
	 * @param accessToken
	 */
	public void setAccessToken(String appId, String accessToken);
	
	/**
	 * @param appId
	 * @param accessToken
	 * @return
	 */
	public boolean containsAccessToken(String appId, String accessToken);
	
	/**
	 * @param appId
	 */
	public void delAccessToken(String appId);
	
}
