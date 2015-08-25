/**
 * 
 */
package com.wisedu.amp.fserver.auth.entity;

import java.io.Serializable;

/**
 * 访问令牌实体类,配合应用编号，校验请求的访问权限
 * 
 * @author zengxianping
 *
 */
public class AccessToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private String appId;// 应用编号
	private String token;// 令牌

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", appId=" + appId + ", token="
				+ token + "]";
	}

}
