package com.maoxiaobing.util;

import java.util.List;
import java.util.Properties;

import org.springframework.util.Assert;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	private static Jedis jedis;
	static {
		Properties p = StreamUtil.loadProperties("conf/redis.properties");
		jedis = new Jedis(p.getProperty("host"), Integer.valueOf(p
				.getProperty("port")));
		jedis.auth(p.getProperty("auth"));
	}

	/**
	 * 保存值
	 * 
	 * @param key
	 * @param value
	 * @return 返回的code
	 */
	public static String set(String key, String value) {
		return jedis.set(key, value);
	}

	/**
	 * 设置值
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            寿命
	 * @return 返回的code
	 */
	public static String set(String key, String value, int seconds) {
		return jedis.setex(key, seconds, value);
	}

	/**
	 * 保存如果不存在
	 * 
	 * @param key
	 * @param value
	 * @return 返回的code
	 */
	public static String setIfNotExists(String key, String value) {
		return jedis.set(key, value, "NX");
	}

	/**
	 * 保存如果已经存在
	 * 
	 * @param key
	 * @param value
	 * @return 返回的code
	 */
	public static String setIfExists(String key, String value) {
		return jedis.set(key, value, "XX");
	}

	/**
	 * 查询key值
	 * 
	 * @param key
	 * @return key值
	 */
	public static String get(String key) {
		return jedis.get(key);
	}

	/**
	 * 批量删除键值
	 * 
	 * @param keys
	 * @return 删除的个数
	 */
	public static Long delete(String... keys) {
		return jedis.del(keys);
	}

	/**
	 * 删除单个键值
	 * 
	 * @param key
	 * @return 删除的个数
	 */
	public static Long delete(String key) {
		return jedis.del(key);
	}

	/**
	 * 删除键值列表
	 * 
	 * @param keys
	 * @return
	 */
	public static Long delete(List<String> keys) {
		Assert.notEmpty(keys);
		return jedis.del(keys.toArray(new String[keys.size()]));
	}

	/**
	 * 检测是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean ifExists(String key) {
		return jedis.exists(key);
	}

	/**
	 * 设置指定key的寿命
	 * 
	 * @param key
	 * @param seconds
	 */
	public static void setExpireTime(String key, int seconds) {
		jedis.expire(key, seconds);
	}

	public static Jedis getJedis() {
		return jedis;
	}

	public static void setJedis(Jedis jedis) {
		RedisUtil.jedis = jedis;
	}

}
