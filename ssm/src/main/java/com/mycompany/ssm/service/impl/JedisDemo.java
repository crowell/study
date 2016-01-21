package com.mycompany.ssm.service.impl;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
	
	public static JedisPoolConfig jpc = new JedisPoolConfig();
	public static JedisPoolConfig getJpc() {
		return jpc;
	}

	public static void setJpc(JedisPoolConfig jpc) {
		JedisDemo.jpc = jpc;
	}

	public static JedisPool jp;

	public static JedisPool getJp() {
		return jp;
	}

	public static void setJp(JedisPool jp) {
		JedisDemo.jp = jp;
	}
	

}
