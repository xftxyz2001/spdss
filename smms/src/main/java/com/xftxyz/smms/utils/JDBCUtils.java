package com.xftxyz.smms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        // 1.加载配置文件
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

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
}
