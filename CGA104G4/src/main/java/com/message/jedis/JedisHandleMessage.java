package com.message.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String memId, String storeId) {
		String key = new StringBuilder(memId).append(":").append(storeId).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String memId, String storeId, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String memKey = new StringBuilder(memId).append(":").append(storeId).toString();
		String storeKey = new StringBuilder(memId).append(":").append(storeId).toString();
		Jedis jedis = pool.getResource();
		jedis.rpush(memKey, message);
		jedis.rpush(storeKey, message);

		jedis.close();
	}

}