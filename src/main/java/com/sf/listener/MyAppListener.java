package com.sf.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 描述：自定义服务器生命周期监听器
 * @author 80002888
 * @date   2018年6月20日
 */
public class MyAppListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 服务器（tomcat）启动时，设置属性项目的访问路径到服务器域对象ServletContext中
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("ctp", servletContext.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (sce != null) {
			sce.getServletContext().removeAttribute("ctp");
		}
	}

}
