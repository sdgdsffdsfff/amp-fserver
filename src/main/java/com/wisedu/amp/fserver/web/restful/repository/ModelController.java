package com.wisedu.amp.fserver.web.restful.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.foxbpm.engine.ModelService;
import org.foxbpm.engine.ProcessEngine;
import org.foxbpm.engine.ProcessEngineManagement;
import org.foxbpm.engine.exception.FoxBPMException;
import org.foxbpm.engine.exception.FoxbpmPluginException;
import org.foxbpm.engine.repository.Deployment;
import org.foxbpm.engine.repository.DeploymentBuilder;
import org.foxbpm.engine.repository.ProcessDefinition;
import org.foxbpm.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wisedu.amp.fserver.web.restful.PageResponse;

/**
 * 模型相关接口
 * @author ych
 *
 */
@RestController
@RequestMapping(value = "/model")
public class ModelController {
	
	@Autowired
	private ModelService modelService;
	
	/**
	 * 流程定义发布
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/deployments", method = RequestMethod.POST)
	public String deploy(InputStream input){
		String processDefinitionKey = null;
		try {
			if(input == null){
				throw new FoxbpmPluginException("请求中必须包含文件流", "Rest服务");
			}
			ZipInputStream zip = new ZipInputStream(input);
			DeploymentBuilder deploymentBuilder = modelService.createDeployment();
			deploymentBuilder.addZipInputStream(zip);
			Deployment deployment = deploymentBuilder.deploy();
			ProcessDefinitionQuery processDefinitionQuery = modelService.createProcessDefinitionQuery();
			processDefinitionKey = processDefinitionQuery.deploymentId(deployment.getId()).singleResult().getId();
		} catch (Exception e) {
			if (e instanceof FoxBPMException) {
				throw (FoxBPMException) e;
			}
			throw new FoxBPMException(e.getMessage(), e);
		}		
		return processDefinitionKey;
	}
	
	/**
	 * 查询流程定义，目前仅做设计器需要的部分
	 * @param processKey
	 * @return
	 */
	@RequestMapping(value = "/process-definitions", method = RequestMethod.GET)
	public PageResponse getDefinitions(@RequestParam("key") String processKey){
		ProcessEngine engine = ProcessEngineManagement.getDefaultProcessEngine();
		ProcessDefinitionQuery processDefinitionQuery = engine.getModelService().createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionKey(processKey);
		List<ProcessDefinition> processDefinitions = processDefinitionQuery.list();
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		for (ProcessDefinition process : processDefinitions) {
			Map<String,Object> processAttrMap = process.getPersistentState();
			processAttrMap.put("description", process.getDescription());
			results.add(processAttrMap);
		}
		PageResponse result = new PageResponse();
		result.setData(results);
		result.setTotal(processDefinitions.size());
		return result;
	}
	
	/**
	 * 更新流程部署
	 * @param input
	 * @param request
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping(value = "/deployments/{deploymentId}", method = RequestMethod.PUT)
	public String update(InputStream input,@PathVariable String deploymentId){
		try {
			if(input == null){
				throw new FoxbpmPluginException("请求中必须包含文件流", "Rest服务");
			}
			DeploymentBuilder deploymentBuilder = modelService.createDeployment();
			ZipInputStream zip = new ZipInputStream(input);
			deploymentBuilder.updateDeploymentId(deploymentId);
			deploymentBuilder.addZipInputStream(zip);
			deploymentBuilder.deploy();
		} catch (Exception e) {
			if (e instanceof FoxBPMException) {
				throw (FoxBPMException) e;
			}
			throw new FoxBPMException(e.getMessage(), e);
		}
		
		return "SUCCESS";
	}
	
	/**
	 * 删除流程定义
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping(value = "/deployments/{deploymentId}", method = RequestMethod.DELETE)
	public String deleteDeployment(@PathVariable String deploymentId){
		modelService.deleteDeployment(deploymentId);
		return "SUCCESS";
	}
}
