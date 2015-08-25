package com.wisedu.amp.fserver.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 抽象的测试用例
 * 
 * @author zengxianping
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
		@ContextConfiguration(name = "child", locations = {
				"classpath:applicationContext-mvc.xml", "classpath:applicationContext-redis.xml" }) })
public abstract class AbstractTestCase {
	@Autowired
	private WebApplicationContext wac;
	protected MockMvc mockMvc;
	protected HttpHeaders headers;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		// 构建通用的http头
		headers = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appId", "101947");
		headers.set("accessToken", "nizhenshai");
	}

}
