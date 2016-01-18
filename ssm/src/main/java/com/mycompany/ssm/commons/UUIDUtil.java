package com.mycompany.ssm.commons;

import java.util.UUID;

/**
 * @description UUID工具类
 * @author  wulg
 */
public class UUIDUtil {

	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

}
