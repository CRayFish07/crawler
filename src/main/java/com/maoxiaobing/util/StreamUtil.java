package com.maoxiaobing.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StreamUtil {

	private static ClassLoader loader = StreamUtil.class.getClassLoader();

	public static Properties loadProperties(String path) {
		InputStream in = loader.getResourceAsStream(path);
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
