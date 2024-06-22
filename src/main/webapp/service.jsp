<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>服务页面</title>
</head>
<body>
    <%
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int priv = session.getAttribute("priv") != null ? (int) session.getAttribute("priv") : 3;
        if (username == null) {
            response.sendRedirect("login.jsp");
        } else {  
            if (priv == 1) {
            	out.println("欢迎管理员 [" + username+"] 登录本系统");%><br><% 
                out.println("<a href='show-userlist.jsp'>查看所有用户</a>");
            }else{
            	out.println("欢迎用户 [" + username+"] 登录本系统 |");
            	out.println("<a href='login.jsp'>退出登录</a>");%><br><%
            	out.println("<a href='UserServlet?action=selfDisable'>账户停用</a>");
            }
        }
    %>
<br />    
</body>
</html>

