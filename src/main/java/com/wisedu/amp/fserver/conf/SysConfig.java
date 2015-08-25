package com.wisedu.amp.fserver.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 流程中心系统配置类
 * 
 * @author zengxianping
 *
 */
@Component
public class SysConfig {

	@Value("${portal.appId}")
	private String portalAppId;// 门户的特殊的appId

	@Value("${enableAuth}")
	private boolean enableAuth;// 启用认证

	@Value("${enableIpWhiteList}")
	private boolean enableIpWhiteList;// 开启ip白名单

	public String getPortalAppId() {
		return portalAppId;
	}

	public void setPortalAppId(String portalAppId) {
		this.portalAppId = portalAppId;
	}

	public boolean isEnableAuth() {
		return enableAuth;
	}

	public void setEnableAuth(boolean enableAuth) {
		this.enableAuth = enableAuth;
	}

	public boolean isEnableIpWhiteList() {
		return enableIpWhiteList;
	}

	public void setEnableIpWhiteList(boolean enableIpWhiteList) {
		this.enableIpWhiteList = enableIpWhiteList;
	}
}
