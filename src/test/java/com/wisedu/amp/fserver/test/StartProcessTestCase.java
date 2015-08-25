package com.wisedu.amp.fserver.test;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisedu.amp.fserver.web.restful.runtime.process.StartProcessRequest;

/**
 * 测试启动流程测试用例
 * 
 * @author zengxianping
 *
 */
public class StartProcessTestCase extends AbstractTestCase {

	/**
	 * 启动流程访问地址
	 */
	private static final String START_PROCESS_URL = "/runtime/process-instances";

	@Test
	public void testStartProcess() throws Exception {

		MvcResult mvcResult = mockMvc.perform(buildRequest())
				.andDo(MockMvcResultHandlers.print()).andReturn();
		System.out.println(mvcResult.getResolvedException()
				.getLocalizedMessage());
		System.out.println(mvcResult.getResponse().getContentAsString());
	}

	/**
	 * 构建post请求
	 * 
	 * @return
	 */
	private RequestBuilder buildRequest() {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(START_PROCESS_URL).headers(headers)
				.content(buildRequestBody());
		return requestBuilder;
	}

	/**
	 * 构建请求体
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	private String buildRequestBody() {
		StartProcessRequest startProcessRequest = new StartProcessRequest();
		startProcessRequest.setProcessKey("test_wisedurole_1");
		startProcessRequest.setBizKey("bizKey_123456");
//		 startProcessRequest.setInitiator("31071039");
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = null;
		try {
			requestBody = mapper.writeValueAsString(startProcessRequest);
		} catch (JsonProcessingException e) {
			System.out.println("将启动流程请求体转换成json格式时出错:"
					+ e.getLocalizedMessage());
		}
		return requestBody;
	}
}
