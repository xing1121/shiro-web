package com.sf.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sf.domain.RolesPermissions;
import com.sf.domain.RolesPermissionsExample;
import com.sf.domain.UserRoles;
import com.sf.domain.UserRolesExample;
import com.sf.domain.Users;
import com.sf.domain.UsersExample;
import com.sf.domain.UsersExample.Criteria;
import com.sf.mapper.RolesPermissionsMapper;
import com.sf.mapper.UserRolesMapper;
import com.sf.mapper.UsersMapper;

/**
 * 描述：自定义Realm
 * @author 80002888
 * @date   2018年7月17日
 */
public class MyRealm extends AuthorizingRealm{

	/**
	 * 盐值
	 */
	private String salt;
	
	/**
	 * 是否加盐
	 */
	private boolean saltStatus;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private UserRolesMapper userRolesMapper;
	
	@Autowired
	private RolesPermissionsMapper rolesPermissionsMapper;

	//不加密：123456
	//MD5加密：e10adc3949ba59abbe56e057f20f883e
	//MD5加密，盐值"HAHA"：1785067ca6f96590d4e11b1385ab9dc5
	
	/**
	 * 获取角色、权限的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户名
		String userName = (String) principals.getPrimaryPrincipal();
		// 根据用户名从数据库获取角色
		Set<String> roles = getRolesByUserName(userName);
		if (roles == null || roles.size() == 0) {
			return null;
		}
		// 构建凭证
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roles);
		// 根据角色从数据库获取权限
		Set<String> permissions = new HashSet<>();
		for (String role : roles) {
			Set<String> ps = getPermissionsByRole(role);
			if (ps == null || ps.size() == 0) {
				continue;
			}
			permissions.addAll(ps);
		}
		if (permissions.size() != 0) {
			simpleAuthorizationInfo.setStringPermissions(permissions);
		}
		return simpleAuthorizationInfo;
	}
	
	/**
	 * 认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从主体传过来的认证信息中获取用户名
		String userName = (String) token.getPrincipal();
		// 根据用户名从数据库获取密码
		String password = getPasswordByUserName(userName);
		if (password == null) {
			return null;
		}
		// 构造凭证
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, password, "MR");
		// 判断是否加盐
		if (saltStatus) {
			simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(salt));
		}
		return simpleAuthenticationInfo;
	}

	/**
	 * 从数据库根据用户名获取密码
	 *	@ReturnType	String 
	 *	@Date	2018年7月17日	下午2:18:18
	 *  @Param  @return
	 */
	private String getPasswordByUserName(String userName) {
		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<Users> list = usersMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0).getPassword();
	}
	
	/**
	 * 从数据库根据用户名获取所拥有的角色
	 *	@ReturnType	Set<String> 
	 *	@Date	2018年7月17日	下午2:27:55
	 *  @Param  @param userName
	 *  @Param  @return
	 */
	private Set<String> getRolesByUserName(String userName) {
		System.out.println("从数据库中获取权限数据！");
		UserRolesExample example = new UserRolesExample();
		com.sf.domain.UserRolesExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<UserRoles> list = userRolesMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.stream().map((x)->x.getRoleName()).collect(Collectors.toSet());
	}
	
	/**
	 * 从数据库根据角色获取所拥有的权限
	 *	@ReturnType	Set<String> 
	 *	@Date	2018年7月17日	下午2:27:55
	 *  @Param  @param userName
	 *  @Param  @return
	 */
	private Set<String> getPermissionsByRole(String role) {
		RolesPermissionsExample example = new RolesPermissionsExample();
		com.sf.domain.RolesPermissionsExample.Criteria criteria = example.createCriteria();
		criteria.andRoleNameEqualTo(role);
		List<RolesPermissions> list = rolesPermissionsMapper.selectByExample(example );
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.stream().map((x)->x.getPermission()).collect(Collectors.toSet());
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isSaltStatus() {
		return saltStatus;
	}

	public void setSaltStatus(boolean saltStatus) {
		this.saltStatus = saltStatus;
	}
	
}
