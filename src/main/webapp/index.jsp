<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
</head>
<body>
	<h2>欢迎光临，请登录</h2>
	
	<hr>
	
	<form action="${ctp}/login" method="post">
		用户名：<input type="text" name="username"><br><br>
		密码&nbsp;:<input type="text" name="password"><br>
		记住我：<input type="checkbox" name="rememberMe"><br><br>
		<input type="submit" value="提交">
	</form>
	
</body>
</html>