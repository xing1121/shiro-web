package com.sf.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 描述：自定义角色Filter，有一个满足就是true
 * @author 80002888
 * @date   2018年7月18日
 */
public class RolesOrFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// 获得主体
		Subject subject = this.getSubject(request, response);
		if (mappedValue == null ) {
			return false;
		}
		// 获得配置中的角色数组
		String[] roles = (String[]) mappedValue;
		if (roles.length == 0) {
			return false;
		}
		// 有一个满足条件就返回true
		for (String role : roles) {
			if (subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

}
