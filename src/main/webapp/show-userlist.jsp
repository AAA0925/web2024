<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page import="com.tty.servlet.DBUtil" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
</head>
<body>

    <%
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int priv = session.getAttribute("priv") != null ? (int) session.getAttribute("priv") : 3;
        if (username == null || priv != 1) {
            response.sendRedirect("login.jsp");
        } else {
        	out.println("欢迎管理员 [" + username+"] 登录本系统 |");
        	out.println("<a href='login.jsp'>退出登录</a>");
        	//out.println("<a href='show-userlist.jsp'>查看所有用户</a>");
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = DBUtil.getConnection();
                String sql = "SELECT * FROM user";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>用户名</th><th>密码</th><th>权限</th><th>操作</th></tr>");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String pwd = rs.getString("pwd");
                    int userPriv = rs.getInt("priv");
                    String privStr = userPriv == 1 ? "管理员" : (userPriv == 2 ? "普通用户" : "游客");
                    out.println("<tr>");
                    out.println("<td>" + id + "</td>");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + pwd + "</td>");
                    out.println("<td>" + privStr + "</td>");
                    out.println("<td>");
                    if (userPriv == 3) {
                        out.println("<a href='UserServlet?action=verify&userId=" + id + "'>审核通过</a>");
                    } else if (userPriv == 2) {
                        out.println("<a href='UserServlet?action=disable&userId=" + id + "'>账户停用</a>");
                    }else{
                    	out.println("管理员");
                    }
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn, stmt, rs);
            }
        }
    %>
    
</body>
</html>
