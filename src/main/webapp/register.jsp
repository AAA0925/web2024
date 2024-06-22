<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
</head>
<body>
<h2>注册</h2>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="register">
        用户名: <input type="text" name="username"><br>
        密码: <input type="password" name="password"><br>
        <input type="submit" value="注册">
    </form>
</body>
</html>
