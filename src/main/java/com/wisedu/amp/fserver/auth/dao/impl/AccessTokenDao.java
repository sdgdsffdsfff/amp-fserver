/**
 * 
 */
package com.wisedu.amp.fserver.auth.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.foxbpm.engine.impl.util.GuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wisedu.amp.fserver.auth.dao.IAccessTokenDao;
import com.wisedu.amp.fserver.auth.entity.AccessToken;

/**
 * 访问令牌数据操作实现类
 * @author zengxianping
 *
 */
@Repository
public class AccessTokenDao implements IAccessTokenDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<AccessToken> accessTokenRowMapper = new RowMapper<AccessToken>() {

		public AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
			AccessToken accessToken = new AccessToken();
			accessToken.setId(rs.getString("ID"));
			accessToken.setAppId(rs.getString("APP_ID"));
			accessToken.setToken(rs.getString("TOKEN"));
			return accessToken;
		}
	};

	/**
	 * 为应用生成访问令牌，每个应用有且只有一个访问令牌
	 * 
	 * @param appId
	 *            应用编号
	 * @return 访问令牌
	 */
	public String genAccessToken(String appId) {
		// 目前直接用guid作为accessToken，后期可能会根据一些规则生成
		String accessToken = GuidUtil.CreateGuid();
		String insertSql = "INSERT INTO AUTH_ACCESS_TOKEN(ID,APP_ID,TOKEN) VALUES(?,?,?)";
		jdbcTemplate.update(insertSql, new Object[] { GuidUtil.CreateGuid(),
				accessToken, appId });
		return accessToken;
	}

	/**
	 * 根据应用编号查找方法令牌
	 * 
	 * @param appId
	 *            应用编号
	 * @return 访问令牌实体
	 */
	public AccessToken findAccessTokenByAppId(final String appId) {
		String selectSql = "SELECT ID,APP_ID,TOKEN FROM AUTH_ACCESS_TOKEN WHERE APP_ID=?";
		return jdbcTemplate.queryForObject(selectSql, new Object[] { appId },
				accessTokenRowMapper);
	}

	/**
	 * 查询所有的访问令牌
	 * @return 访问令牌集合
	 */
	public List<AccessToken> findAll() {
		String selectSql = "SELECT ID,APP_ID,TOKEN FROM AUTH_ACCESS_TOKEN";
		return jdbcTemplate.query(selectSql, accessTokenRowMapper);
	}
}
