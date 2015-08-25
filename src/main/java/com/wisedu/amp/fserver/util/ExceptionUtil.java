package com.wisedu.amp.fserver.util;

import org.foxbpm.engine.exception.FoxBPMException;

/**
 * 异常工具类
 * 
 * @author zengxianping
 *
 */
public class ExceptionUtil {
	/**
	 * 从foxBPMException中提取异常码
	 * 
	 * @param foxBPMException
	 * @return
	 */
	public static String extractExceptionCode(FoxBPMException foxBPMException) {
		if (foxBPMException == null) {
			throw new IllegalArgumentException("异常实例不能为空！");
		}
		return foxBPMException.getMessage().split(":")[0];
	}
}
