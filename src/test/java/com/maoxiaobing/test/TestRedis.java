package com.maoxiaobing.test;

import org.junit.Test;

import com.maoxiaobing.util.RedisUtil;

public class TestRedis {
	@Test
	public void testUtil(){
		System.out.println(RedisUtil.set("key2", "213"));
		RedisUtil.delete("key2");
	}
}
