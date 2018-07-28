package com.sf.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

public class MySessionManager extends DefaultWebSessionManager{

	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		// 获取请求
		ServletRequest request = null;
		if (sessionKey instanceof WebSessionKey) {
			request = ((WebSessionKey)sessionKey).getServletRequest();
		}
		// 获取请求中的sessionId
		Serializable sessionId = getSessionId(sessionKey);
		// 先从request中取session，取到就返回
		if (request != null && sessionId != null) {
			Session session = (Session) request.getAttribute(sessionId.toString());
			if (session != null) {
				return session;
			}
		}
		// 取不到从自定义的sessionDao中取并放入request中
		Session session = super.retrieveSession(sessionKey);
		if (request != null && sessionId != null) {
			request.setAttribute(sessionId.toString(), session);
		}
		return session;
	}
	
}
