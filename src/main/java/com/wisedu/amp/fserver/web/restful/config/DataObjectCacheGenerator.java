package com.wisedu.amp.fserver.web.restful.config;

import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.foxbpm.engine.ProcessEngineManagement;
import org.foxbpm.engine.exception.FoxBPMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataObjectCacheGenerator implements IZipGenerator{

	private static Logger log = LoggerFactory.getLogger(DataObjectCacheGenerator.class);
	 
	public void generate(ZipOutputStream out) {
		log.debug("开始处理bizData.data...");
		try{
			List<Map<String,Object>> list = ProcessEngineManagement.getDefaultProcessEngine().getModelService().getAllBizObjects();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(out, JsonEncoding.UTF8);
			String tmpEntryName = "cache/bizData.data";
			ZipEntry zipEntry = new ZipEntry(tmpEntryName);
			zipEntry.setMethod(ZipEntry.DEFLATED);// 设置条目的压缩方式
			out.putNextEntry(zipEntry);
			jsonGenerator.writeObject(list);
			out.closeEntry();
			log.debug("处理bizData.data文件完毕");
		}catch(Exception ex){
			log.error("解析bizData.data文件失败！生成zip文件失败！");
			throw new FoxBPMException("解析bizData.data文件失败",ex);
		}
	}
}
