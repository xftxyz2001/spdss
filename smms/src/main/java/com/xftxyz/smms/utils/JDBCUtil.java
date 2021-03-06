package com.xftxyz.smms.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        // 1.加载配置文件
        Properties pros = FileUtil.getProperties("jdbc.properties");

        // 2.读取配置信息
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        // 3.加载驱动
        Class.forName(driverClass);

        // 4.获取连接
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
