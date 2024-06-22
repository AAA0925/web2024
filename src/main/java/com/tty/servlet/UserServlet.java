package com.tty.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    	String action = request.getParameter("action");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            if (action.equals("register")) {
                // 处理用户注册逻辑
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String sql = "INSERT INTO user (name, pwd) VALUES (?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
                response.sendRedirect("login.jsp?message=registered");
            } else if (action.equals("login")) {
                // 处理用户登录逻辑
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String sql = "SELECT * FROM user WHERE name = ? AND pwd = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    int priv = rs.getInt("priv");
                    if (priv == 3) {
                        response.sendRedirect("login.jsp?error=unverified");
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);
                        session.setAttribute("priv", priv);
                        response.sendRedirect("service.jsp");
                    }
                } else {
                    response.sendRedirect("login.jsp?error=invalid");
                }
            } else if (action.equals("logout")) {
                // 处理用户退出登录逻辑
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("login.jsp");
            } else if (action.equals("verify")) {
                // 处理用户审核逻辑
                String userId = request.getParameter("userId");
                String sql = "UPDATE user SET priv = 2 WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(userId));
                stmt.executeUpdate();
                response.sendRedirect("show-userlist.jsp");
            } else if (action.equals("disable")) {
                // 处理用户停用逻辑
                String userId = request.getParameter("userId");
                String sql = "UPDATE user SET priv = 3 WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(userId));
                stmt.executeUpdate();
                response.sendRedirect("show-userlist.jsp");
            }else if (action.equals("selfDisable")) {
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("username");
                String sql = "UPDATE user SET priv = 3 WHERE name = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.executeUpdate();
                session.invalidate();
                response.sendRedirect("login.jsp?error=unverified");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
