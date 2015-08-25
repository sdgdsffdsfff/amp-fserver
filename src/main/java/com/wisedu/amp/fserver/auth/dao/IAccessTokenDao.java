package com.wisedu.amp.fserver.auth.dao;

import java.util.List;

import com.wisedu.amp.fserver.auth.entity.AccessToken;

/**
 * 访问令牌数据操作接口
 * @author zengxianping
 *
 */
public interface IAccessTokenDao {
	/**
	 * 为应用生成访问令牌，每个应用有且只有一个访问令牌
	 * 
	 * @param appId
	 *            应用编号
	 * @return 访问令牌
	 */
	public String genAccessToken(String appId);

	/**
	 * 根据应用编号查找方法令牌
	 * 
	 * @param appId
	 *            应用编号
	 * @return 访问令牌实体
	 */
	public AccessToken findAccessTokenByAppId(String appId);

	/**
	 * 查询所有的访问令牌
	 * 
	 * @return 访问令牌集合
	 */
	public List<AccessToken> findAll();
}
