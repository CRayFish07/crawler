package com.maoxiaobing.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.maoxiaobing.util.HttpUtil;

public class TestHttpUtil {
	@Test
	public void testGetPostJsonResult() {
		StringBuffer url = new StringBuffer();
		url.append("http://localhost/crawler");
		url.append("/");
		url.append("mock/getConfig.do");
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "毛小冰");
		params.put("password", "123456");
		System.out.println(HttpUtil.getPostJsonResult(url.toString(), params));
	}

	@Test
	public void testValidateWeixinServer() {
		StringBuffer url = new StringBuffer();
		url.append("http://localhost/crawler");
		url.append("/");
		url.append("mwserver/echo.do");
		Map<String, String> params = new HashMap<String, String>();
		params.put("signature", "毛小冰");
		params.put("timestamp", "123456");
		params.put("nonce", "123456");
		params.put("echostr", "123456");
		String result = HttpUtil.getPostJsonResult(url.toString(), params);
		System.out.println(result);
	}
}
