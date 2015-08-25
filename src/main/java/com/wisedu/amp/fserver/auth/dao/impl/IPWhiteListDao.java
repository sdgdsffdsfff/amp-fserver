/**
 * 
 */
package com.wisedu.amp.fserver.auth.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wisedu.amp.fserver.auth.dao.IWhiteListDao;
import com.wisedu.amp.fserver.auth.entity.IPWhiteList;

/**
 * ip白名单数据操作实现类
 * @author zengxianping
 *
 */
@Repository
public class IPWhiteListDao implements IWhiteListDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<IPWhiteList> ipWhiteListRowMapper = new RowMapper<IPWhiteList>() {

		public IPWhiteList mapRow(ResultSet rs, int rowNum) throws SQLException {
			IPWhiteList ipWhiteList = new IPWhiteList();
			ipWhiteList.setId(rs.getString("ID"));
			ipWhiteList.setAppId(rs.getString("APP_ID"));
			ipWhiteList.setIp(rs.getString("IP"));
			return ipWhiteList;
		}
	};

	/**
	 * 一个应用包含多个ip白名单， 根据应用编号查找ip白名单列表
	 * 
	 * @param appId
	 *            应用编号
	 * @return 访问令牌实体
	 */
	public Set<IPWhiteList> findIpWhiteListsByAppId(final String appId) {
		String selectSql = "SELECT ID,APP_ID,IP FROM AUTH_WHITE_LIST WHERE APP_ID=?";
		List<IPWhiteList> ipWhiteLists = jdbcTemplate.query(selectSql,
				new Object[] { appId }, ipWhiteListRowMapper);
		return new HashSet<IPWhiteList>(ipWhiteLists);
	}

	/**
	 * 查询所有的ip白名单列表
	 * 
	 * @return 访问令牌集合
	 */
	public Map<String, Set<IPWhiteList>> findAll1() {
		Map<String, Set<IPWhiteList>> ipWhiteLists = new HashMap<String, Set<IPWhiteList>>();
		List<String> appIds = findAllAppIds();
		if (appIds != null && appIds.size() > 0) {
			for (String appId : appIds) {
				ipWhiteLists.put(appId, findIpWhiteListsByAppId(appId));
			}
		}
		return ipWhiteLists;
	}

	/**
	 * @return 认证白名单表中所有的应用编号集合
	 */
	private List<String> findAllAppIds() {
		return jdbcTemplate.queryForList(
				"SELECT DISTINCT APP_ID FROM AUTH_WHITE_LIST", String.class);
	}

	/**
	 * 查询所有的ip白名单集合
	 * 
	 * @return ip白名单集合
	 */
	public Map<String, Set<String>> findAll2() {
		Map<String, Set<String>> ipWhiteLists = new HashMap<String, Set<String>>();
		List<String> appIds = findAllAppIds();
		if (appIds != null && appIds.size() > 0) {
			String selectSql = "SELECT IP FROM AUTH_WHITE_LIST WHERE APP_ID=?";
			for (String appId : appIds) {
				List<String> ipWhiteList = jdbcTemplate.queryForList(selectSql,
						new Object[] { appId }, String.class);
				ipWhiteLists.put(appId, new HashSet<String>(ipWhiteList));
			}
		}
		return ipWhiteLists;
	}
}
