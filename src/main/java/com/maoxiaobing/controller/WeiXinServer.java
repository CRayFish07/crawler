package com.maoxiaobing.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maoxiaobing.util.MsgHandler;

@Controller
@RequestMapping("/mwserver")
public class WeiXinServer {
	@RequestMapping(value = "/echo")
	public void weixinServer(HttpServletRequest request,
			HttpServletResponse response) {
		OutputStream os = null;
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			boolean isGet = request.getMethod().toLowerCase().equals("get");
			String result = "";
			String xml;
			if (isGet) {
				String echostr = request.getParameter("echostr");
				if (echostr != null && echostr.length() > 0) {
					result = echostr;
				}
			} else {
				StringBuffer sb = new StringBuffer();
				InputStream is = request.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String s = "";
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				xml = sb.toString();
				System.out.println("接收的结果为：" + xml
						+ "*****************************");
				result = MsgHandler.processWechatMag(xml);
				System.out.println("返回结果为：" + result
						+ "***********************");
			}
			os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.close();
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
