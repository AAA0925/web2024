<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>登录</h2>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="login">
        用户名: <input type="text" name="username"><br>
        密码: <input type="password" name="password"><br>
        <input type="submit" value="登录">
        <a href="register.jsp">注册</a>
    </form>
    <%
        String error = request.getParameter("error");
        if ("unverified".equals(error)) {
            out.println("用户名还未审核通过，请联系管理员【yyds】");
        } else if ("invalid".equals(error)) {
            out.println("用户名或密码错误");
        }
        String message = request.getParameter("message");
        if ("registered".equals(message)) {
            out.println("注册成功，请等待审核");
        }
    %>
</body>
</html>
