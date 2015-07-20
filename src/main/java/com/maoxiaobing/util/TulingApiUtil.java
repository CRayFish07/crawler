package com.maoxiaobing.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 图灵机器人api
 * 
 * @author maoxiaobing
 *
 */
public class TulingApiUtil {
	public static String getTulingResult(String content) {
		String apiUrl = "http://www.tuling123.com/openapi/api?key=5b9b82e10f0bef1a63b805f2709ec11a&info=";
		String param = "";
		try {
			param = apiUrl + URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpGet request = new HttpGet(param);
		String result = "";
		try {
			HttpResponse response = HttpClients.createDefault()
					.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (null == result) {
			return "对不起，你说的话真是太高深了……";
		}
		try {
			JSONObject json = JSONObject.parseObject(result);
			if (100000 == json.getIntValue("code")) {
				result = json.getString("text");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}