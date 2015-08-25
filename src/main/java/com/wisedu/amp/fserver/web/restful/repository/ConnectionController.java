package com.wisedu.amp.fserver.web.restful.repository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 连接控制器，测试流程服务是否可用
 * 
 * @author zengxianping
 *
 */
@RestController
public class ConnectionController {
	@RequestMapping(value = "/testConnection", method = RequestMethod.GET)
	public String testConnection() {
		return "SUCCESS";
	}
}
