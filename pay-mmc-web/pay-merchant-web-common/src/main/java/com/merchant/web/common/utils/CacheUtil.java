package com.merchant.web.common.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author williams.qian Redis Cache Util
 */
public class CacheUtil {

	/**
	 * Distribute Redis Lock
	 * 
	 * @param redisTemplate
	 * @param key
	 * @return
	 */
	public static Boolean lock(RedisTemplate<String, String> redisTemplate, String key) {
		Boolean r = redisTemplate.boundValueOps(key).setIfAbsent("1");
		if (null != r && r) {
			redisTemplate.expire(key, 3, TimeUnit.HOURS);
		}
		return r;
	}

	/**
	 * Distribute Redis Lock Set Timeout
	 * 
	 * @param redisTemplate
	 * @param key
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public static Boolean lock(RedisTemplate<String, String> redisTemplate, String key, long timeout,
			TimeUnit timeUnit) {
		Boolean r = redisTemplate.boundValueOps(key).setIfAbsent("1");
		if (null != r && r) {
			redisTemplate.expire(key, timeout, timeUnit);
		}
		return r;
	}

	/**
	 * Distribute Redis unLock
	 * 
	 * @param redisTemplate
	 * @param key
	 */
	public static void unlock(RedisTemplate<String, String> redisTemplate, String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Distribute Redis Left push
	 * 
	 * @param redisTemplate
	 * @param key
	 * @param object
	 * @return
	 */
	public static <V> Long leftPush(RedisTemplate<String, V> redisTemplate, String key, V object) {
		return redisTemplate.boundListOps(key).leftPush(object);
	}

	/**
	 * Distribute Redis Right Push
	 * 
	 * @param redisTemplate
	 * @param key
	 * @param object
	 * @return
	 */
	public static <V> Long rightPush(RedisTemplate<String, V> redisTemplate, String key, V object) {
		return redisTemplate.boundListOps(key).rightPush(object);
	}

	@SafeVarargs
	public static <V> Long add(RedisTemplate<String, V> redisTemplate, String key, V... objects) {
		return redisTemplate.boundSetOps(key).add(objects);
	}

	/**
	 * Get Distribute List Size
	 * 
	 * @param redisTemplate
	 * @param key
	 * @return
	 */
	public static <V> Long getListSize(RedisTemplate<String, V> redisTemplate, String key) {
		return redisTemplate.boundListOps(key).size();
	}

	/**
	 * Get From List
	 * 
	 * @param redisTemplate
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static <V> List<V> getFromList(RedisTemplate<String, V> redisTemplate, String key, long start, long end) {
		return redisTemplate.boundListOps(key).range(start, end);
	}

	/**
	 * Remove From List
	 * 
	 * @param redisTemplate
	 * @param key
	 * @param value
	 * @return
	 */
	public static <V> Long removeFromList(RedisTemplate<String, V> redisTemplate, String key, V value) {
		return redisTemplate.boundListOps(key).remove(0, value);
	}

	/**
	 * Distribute Redis Get From cache
	 * 
	 * @param redisTemplate
	 * @param key
	 * @return
	 */
	public static <V> V getCache(RedisTemplate<String, V> redisTemplate, String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	/**
	 * Distribute Redis Save Cache
	 * 
	 * @param redisTemplate
	 * @param key
	 * @param object
	 * @param expireInSeconds
	 */
	public static <V> void saveCache(RedisTemplate<String, V> redisTemplate, String key, V object,
			Long expireInSeconds) {
		redisTemplate.boundValueOps(key).set(object, expireInSeconds, TimeUnit.SECONDS);
	}

	/**
	 * Distribute Redis Delete
	 * 
	 * @param redisTemplate
	 * @param key
	 */
	public static <V> void delete(RedisTemplate<String, V> redisTemplate, String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Distribute Redis pop
	 * 
	 * @param redisTemplate
	 * @param key
	 * @return
	 */
	public static <V> V rightPop(RedisTemplate<String, V> redisTemplate, String key) {
		return redisTemplate.boundListOps(key).rightPop();
	}

	/**
	 * Distribute Redis Left Pop
	 * 
	 * @param redisTemplate
	 * @param key
	 * @return
	 */
	public static <V> V leftPop(RedisTemplate<String, V> redisTemplate, String key) {
		return redisTemplate.boundListOps(key).leftPop();
	}

	/**
	 * Distribute Redis bpop
	 * 
	 * @param redisTemplate
	 * @param key
	 * @param seconds
	 * @return
	 */
	public static <V> V bpop(RedisTemplate<String, V> redisTemplate, String key, Long seconds) {
		return redisTemplate.boundListOps(key).rightPop(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Execute Redis Lua Script
	 * 
	 * @param redisTemplate
	 * @param script
	 * @param keys
	 * @param args
	 * @return
	 */
	public static <T> T execute(RedisTemplate<String, String> redisTemplate, RedisScript<T> script, List<String> keys,
			Object... args) {
		return redisTemplate.execute(script, keys, args);
	}

	public static <T> String getObjectValueByKey(RedisTemplate<String, String> redisTemplate,String key) {
		return redisTemplate.opsForValue().get(key);
	}




	/**
	 * Increment Element
	 * @param redisTemplate
	 * @param key
	 * @param step
	 * @return
	 */
	public static Long increment(RedisTemplate<String, String> redisTemplate,String key,Long step){
		return redisTemplate.boundValueOps(key).increment(step);
	}

}
