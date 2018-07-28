package com.sf.redis;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtils {

	@Autowired
	private JedisPool jedisPool;

	public void set(byte[] key, byte[] value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} finally {
			jedis.close();
		}
	};
	
	public void set(String key, String value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} finally {
			jedis.close();
		}
	};
	
	public byte[] get(byte[] key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} finally {
			jedis.close();
		}
	}
	
	public String get(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} finally {
			jedis.close();
		}
	}
	
	public void expire(byte[] key, int seconds){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, seconds);
		} finally {
			jedis.close();
		}
	}
	
	public void expire(String key, int seconds){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, seconds);
		} finally {
			jedis.close();
		}
	}
	
	public void del(byte[] key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} finally {
			jedis.close();
		}
	}
	
	public void del(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} finally {
			jedis.close();
		}
	}
	
	public Set<byte[]> keys(byte[] pattern){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.keys(pattern);
		} finally {
			jedis.close();
		}
	}
	
	public Set<String> keys(String pattern){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.keys(pattern);
		} finally {
			jedis.close();
		}
	}
	
	public Long size(){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.dbSize();
		} finally {
			jedis.close();
		}
	}
	
}