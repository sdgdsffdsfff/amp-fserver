package com.wisedu.amp.fserver.auth.dao;

import java.util.Map;
import java.util.Set;

import com.wisedu.amp.fserver.auth.entity.IPWhiteList;

/**
 * 白名单数据操作接口
 * @author zengxianping
 *
 */
public interface IWhiteListDao {
	/**
	 * 一个应用包含多个ip白名单， 根据应用编号查找ip白名单列表
	 * 
	 * @param appId
	 *            应用编号
	 * @return 访问令牌实体
	 */
	public Set<IPWhiteList> findIpWhiteListsByAppId(String appId);

	/**
	 * 查询所有的ip白名单列表
	 * 
	 * @return 访问令牌集合
	 */
	public Map<String, Set<IPWhiteList>> findAll1();

	/**
	 * 查询所有的ip白名单集合
	 * 
	 * @return ip白名单集合
	 */
	public Map<String, Set<String>> findAll2();
}
