package com.sf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.domain.Users;

/**
 * 描述：处理登录的Controller
 * @author 80002888
 * @date   2018年7月18日
 */
@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Users users, boolean rememberMe, HttpServletRequest request, HttpServletResponse response){
		// 获取主体
		Subject subject = SecurityUtils.getSubject();
		// 封装token
		UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
		try {
			// 记住我
			token.setRememberMe(rememberMe);
			// 主体登录
			subject.login(token);
		} catch (Exception e) {
			logger.error("get error->", e);
			try {
				response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/" + "index.jsp");
			} catch (Exception e1) {
				logger.error("get error->" + e1);
			}
		}
		return "success";
	}
	
	/** 以下为测试shiro的注解 */
	
	// 主体必须有admin角色可以成功请求，当参数为多个角色时，必须同时满足
	@RequiresRoles(value={"admin"})
	@RequestMapping(value = "/testRole", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testRole(){
		return "testRole success";
	}
	
	// 主体必须有hahaha角色可以成功请求
	@RequiresRoles(value="hahaha")
	@RequestMapping(value = "/testRole1", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testRole1(){
		return "testRole1 success";
	}
	
	// 主体必须有book:select权限才可以访问
	@RequiresPermissions(value={"book:select"})
	@RequestMapping(value = "/testPermissions", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testPermissions(){
		return "testPermissions success";
	}
	
	// 主体必须有book:insert权限才可以访问
	@RequiresPermissions(value={"book:insert"})
	@RequestMapping(value = "/testPermissions1", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testPermissions1(){
		return "testPermissions1 success";
	}
	
	/** 以下为测试Shiro的配置Filter */
	
	@RequestMapping(value = "/testRole2", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testRole2(){
		return "testRole2 success";
	}
	
	@RequestMapping(value = "/testRole3", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testRole3(){
		return "testRole3 success";
	}
	
	@RequestMapping(value = "/testPermissions2", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testPermissions2(){
		return "testPermissions2 success";
	}
	
	@RequestMapping(value = "/testPermissions3", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testPermissions3(){
		return "testPermissions3 success";
	}
	
	/** 以下为测试自定义角色Filter */
	@RequestMapping(value = "/testRole4", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String testRole4(){
		return "testRole4 success";
	}
	
}
