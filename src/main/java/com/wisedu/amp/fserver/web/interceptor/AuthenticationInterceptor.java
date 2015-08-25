package com.wisedu.amp.fserver.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.foxbpm.engine.impl.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wisedu.amp.fserver.auth.service.IAuthCacheService;
import com.wisedu.amp.fserver.conf.SysConfig;
import com.wisedu.amp.fserver.util.IPUtil;

/**
 * 
 * <p>
 * 流程中心认证拦截器，对客户端请求进行安全性校验，可以通过config.properties属性配置文件，启用是否认证和ip白名单，增加灵活性
 * </p>
 * <p>
 * 校验逻辑如下：
 * </p>
 * <ol>
 * <li>检查请求头中是否有appId，如果没有，则是非法请求，不允许通过；</li>
 * <li>如果请求头有appId,则检查是否启用认证：如果未启用认证（config.properties中enableAuth=false），则直接放行；</li>
 * <li>如果启用认证，则校验是否是特殊的appId(因为门户要查询当前登录用户所有应用的待办任务列表和流程追踪列表，在此将流程作为一个特殊的应用，
 * 为其分配一个特殊的应用编号),如果是，则放行</li>
 * <li>如果不是特殊的appId,则检查是否开启ip白名单：如果开启ip白名单，则校验redis缓存中是否存在，如果存在，则放行；</li>
 * <li>以上都不行，校验appId和accessToken，如果在认证缓存中，则放行；</li>
 * </ol>
 * 
 * 
 * @author zengxianping
 *
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
	@Autowired
	private SysConfig sysConfig;
	@Autowired
	private IAuthCacheService authCacheService;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 参数校验
		String appId = request.getHeader("appId");
		if (StringUtils.isEmpty(appId)) {
			// 对于设计器的请求，直接放行
			String[] safeURIs = new String[] { "/model", "/testConnection",
					"/designer" };
			for (String safeURI : safeURIs) {
				if (request.getRequestURI().indexOf(safeURI) > -1) {
					return true;
				}
			}
			return false;
		}
		// 如果没有启用认证，则直接放行
		if (!sysConfig.isEnableAuth()) {
			Context.setAppId(appId);
			return true;
		}
		// 对于门户的请求，校验其特殊的appId
		if (sysConfig.getPortalAppId().equals(appId)) {
			return true;
		}

		// 如果开启ip白名单
		if (sysConfig.isEnableIpWhiteList()) {
			String ip = IPUtil.getIpAddr(request);
			// 验证IP白名单
			if (authCacheService.containsIp(appId, ip)) {
				// 将appId放入线程副本中
				Context.setAppId(appId);
				return true;
			}
		}

		// 校验accessToken是否合法
		String accessToken = request.getHeader("accessToken");
		if (authCacheService.containsAccessToken(appId, accessToken)) {
			// 将appId放入线程副本中
			Context.setAppId(appId);
			return true;
		}

		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
