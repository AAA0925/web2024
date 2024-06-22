package com.tty.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tty.vo.User;

public class UserDao {
	public boolean findUser(User user) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // 处理驱动程序加载失败的情况
            e.printStackTrace();
            throw new SQLException("MySQL 驱动程序加载失败！");
        }
    	try {
            // 创建连接
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webfinal?useUnicode=true&characterEncoding=utf8mb4&serverTimezone=UTC", "root", "123456");
            
            // 创建 SQL 查询语句
            String sql = "SELECT name, pwd FROM user WHERE uname = ? AND pwd = ?";
            
            // 执行 SQL 查询
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPwd());
            ResultSet rs = ps.executeQuery();
            
            // 处理查询结果
            if (rs.next()) {
                return true; 
            } else {
                return false; 
            }
        } catch (SQLException e) {
            // 处理 SQL 异常
            throw e;
        }
    }
}
