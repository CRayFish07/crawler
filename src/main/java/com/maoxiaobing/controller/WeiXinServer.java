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
import com.maoxiaobing.util.ValidateUtil;

@Controller
@RequestMapping("/mwserver")
public class WeiXinServer {
	@RequestMapping(value = "/echo")
	public void weixinServer(HttpServletRequest request,
			HttpServletResponse response) {
		OutputStream os = null;
		try {
			boolean isGet = request.getMethod().toLowerCase().equals("get");
			String result = "";
			String xml;
			if (isGet) {
				String signature = request.getParameter("signature");
				String timestamp = request.getParameter("timestamp");
				String nonce = request.getParameter("nonce");
				String echostr = request.getParameter("echostr");
				Boolean flag = ValidateUtil.checkSignature(signature,
						timestamp, nonce);
				if (flag) {
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
				result = MsgHandler.processWechatMag(xml);
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
