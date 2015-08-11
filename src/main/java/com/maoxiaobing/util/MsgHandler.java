package com.maoxiaobing.util;

import com.maoxiaobing.entity.ReceiveXmlEntity;
import com.maoxiaobing.entity.ReceiveXmlProcess;

/**
 * 消息转化类，转化为json消息，并调用图灵api，实现消息的回复
 * 
 * @author maoxiaobing
 *
 */
public class MsgHandler {

	public static String processWechatMag(String xml) {
		ReceiveXmlEntity xmlEntity = ReceiveXmlProcess.getMsgEntity(xml);
		String result = "";
		if ("text".endsWith(xmlEntity.getMsgType())) {
			String content = xmlEntity.getContent();
			if (RedisUtil.ifExists(content)) {
				result = RedisUtil.get(content);
			} else {
				result = TulingApiUtil.getTulingResult(xmlEntity.getContent());
				RedisUtil.set(content, result, 60 * 60 * 12);
			}
		}
		result = FormatXmlProcess.formatXmlAnswer(xmlEntity.getFromUserName(),
				xmlEntity.getToUserName(), result);
		return result;
	}
}
