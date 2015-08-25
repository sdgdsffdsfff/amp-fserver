package com.wisedu.amp.fserver.util;

import java.util.Date;
import java.util.Random;

public class OperationUtil {
	public final static String generateOperationId() {
		Random random = new Random(100);
		return String.valueOf((new Date()).getTime()) + String.valueOf(random.nextInt());
	}
}
