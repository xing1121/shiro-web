package com.sf.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import com.sf.redis.RedisUtils;

/**
 * 描述：自定义shiro的sessionDao
 * 		问题：无法获取上一次的session，每次都被浏览器的JSESSIONID取代而导致获取不到session，而创建新的session
 * 		解决：shiro的会话管理器配置<property name="sessionIdCookie.name" value="jsid"></property>，自定义cookie的key即可
 * @author 80002888
 * @date   2018年7月18日
 */
public class RedisSessionDao extends AbstractSessionDAO {

	@Autowired
	private RedisUtils redisUtils;
	
	private final String SHIRO_SESSION_PREFIX = "shiro_session_";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			return;
		}
		redisUtils.set((SHIRO_SESSION_PREFIX + session.getId()).getBytes(), SerializationUtils.serialize(session));
		redisUtils.expire((SHIRO_SESSION_PREFIX + session.getId()).getBytes(), 600);
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			return;
		}
		redisUtils.del((SHIRO_SESSION_PREFIX + session.getId()).getBytes());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<byte[]> set = redisUtils.keys((SHIRO_SESSION_PREFIX + "*").getBytes());
		if (set == null || set.size() == 0) {
			return null;
		}
		Set<Session> sessionSet = new HashSet<>();
		for (byte[] bs : set) {
			Session session = (Session) SerializationUtils.deserialize(bs);
			sessionSet.add(session);
		}
		return sessionSet;
	}

	@Override
	protected Serializable doCreate(Session session) {
		// 随机生成sessionId，其实就是随机数，跟传的参数session没毛关系
		Serializable sessionId = generateSessionId(session);
		// 捆绑session和sessionId
		assignSessionId(session, sessionId);
		redisUtils.set((SHIRO_SESSION_PREFIX + sessionId).getBytes(), SerializationUtils.serialize(session));
		redisUtils.expire((SHIRO_SESSION_PREFIX + session.getId()).getBytes(), 600);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println("doReadSession" + sessionId);
		if (sessionId == null) {
			return null;
		}
		byte[] bs = redisUtils.get((SHIRO_SESSION_PREFIX + sessionId).getBytes());
		if (bs == null || bs.length == 0) {
			return null;
		}
		return (Session) SerializationUtils.deserialize(bs);
	}

}
