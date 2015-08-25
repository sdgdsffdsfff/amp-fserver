/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author ych
 */
package com.wisedu.amp.fserver.web.restful.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * foxbpm 配置文件资源 主要用来向设计器返回引擎中的各配置文件
 * 
 * @author ych
 * 
 */
@RestController
public class FlowConfigResouce {

	/**
	 * @return 流程资源文件
	 * @throws IOException
	 */
	@RequestMapping(value = "/designer/flowconfig", method = RequestMethod.GET, produces = "application/octet-stream")
	public ResponseEntity<byte[]> getConfigFile() throws IOException {
		InputStream configFileInputStream = this.getClass().getClassLoader()
				.getResourceAsStream(FlowResourceService.fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment",
				FlowResourceService.fileName);
		return new ResponseEntity<byte[]>(
				IOUtils.toByteArray(configFileInputStream),/*headers, */HttpStatus.OK);
	}
}
