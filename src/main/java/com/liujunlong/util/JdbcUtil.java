package com.liujunlong.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/29 18:07
 */
public class JdbcUtil {

    private static final String url = "jdbc:mysql://192.168.237.128:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection conn = null;
    private static PreparedStatement pstm = null;

    public static Connection initConnection() {
        if (conn != null) {
            return conn;
        }
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement getPstm(String sql) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
