package com.sf.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.sf.redis.RedisUtils;

@Component
public class RedisCache<K, V> implements Cache<K, V>{

	private final String CACHE_PREFIX = "shiro_cache_";
	
	@Autowired
	private RedisUtils redisUtils;
	
	private byte[] getKey(K key){
		if (key instanceof String) {
			return (CACHE_PREFIX + key).getBytes();
		}
		return SerializationUtils.serialize(key);
	}
	
	@Override
	public V get(K key) throws CacheException {
		System.out.println("从redis中获取权限数据！");
		byte[] bs = redisUtils.get(getKey(key));
		if (bs != null && bs.length != 0) {
			return (V) SerializationUtils.deserialize(bs);
		}
		return null;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		redisUtils.set(getKey(key), SerializationUtils.serialize(value));
		redisUtils.expire(getKey(key), 600);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		V v = this.get(key);
		redisUtils.del(getKey(key));
		return v;
	}
	
	@Override
	public int size() {
		return redisUtils.size().intValue();
	}

	/*********************************************************/
	
	@Override
	public Set<K> keys() {
		return null;
	}

	@Override
	public Collection<V> values() {
		return null;
	}

	@Override
	public void clear() throws CacheException {
		return;
	}


}
