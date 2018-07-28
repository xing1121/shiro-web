<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>成功！</h1>
		<a href="${ctp }/testRole">注解检查角色admin</a><br>
		<a href="${ctp }/testRole1">注解检查角色hahaha</a><br>
		<a href="${ctp }/testPermissions">注解检查权限book:select</a><br>
		<a href="${ctp }/testPermissions1">注解检查权限book:insert</a><br>
		
		<hr>
		
		<a href="${ctp }/testRole2">配置Filter检查角色admin</a><br>
		<a href="${ctp }/testRole3">配置Filter检查角色admin, user</a><br>
		<a href="${ctp }/testPermissions2">配置Filter检查权限book:select</a><br>
		<a href="${ctp }/testPermissions3">配置Filter检查权限book:select, book:insert</a><br>
		
		<hr>
		
		<a href="${ctp }/testRole4">自定义配置Filter检查角色admin, user</a><br>
		
	</body>
</html>