package com.wisedu.amp.fserver.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wisedu.amp.fserver.auth.service.IAuthCacheService;

/**
 * Web上下文监听器，监听Web运行的启动和停止操作
 * 
 * @author zengxianping
 *
 */
public class WebContextListener extends ContextLoaderListener implements
		ServletContextListener {

	private static Logger logger = LoggerFactory
			.getLogger(WebContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("执行系统启动组件的初始化工作...");
		super.contextInitialized(event);
		ServletContext servletContext = event.getServletContext();
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);

		initAuthCache(applicationContext);
	}

	/**
	 * 初始化认证缓存
	 * 
	 * @param applicationContext
	 */
	private void initAuthCache(ApplicationContext applicationContext) {
		IAuthCacheService authCacheService = applicationContext
				.getBean(IAuthCacheService.class);
		authCacheService.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.context.ContextLoaderListener#contextDestroyed
	 * (javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("监听到Web销毁事件，执行系统组件的销毁动作...");
		ServletContext servletContext = event.getServletContext();
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		clearAuthCache(applicationContext);
		super.contextDestroyed(event);
	}

	/**
	 * 清空认证缓存
	 * @param applicationContext
	 */
	private void clearAuthCache(ApplicationContext applicationContext) {
		IAuthCacheService authCacheService = applicationContext
				.getBean(IAuthCacheService.class);
		authCacheService.clear();
	}

}
