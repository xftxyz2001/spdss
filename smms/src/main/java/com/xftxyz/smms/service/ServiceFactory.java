package com.xftxyz.smms.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.xftxyz.smms.utils.JDBCUtil;

public class ServiceFactory {
    // 单例
    private ServiceFactory() {
    }

    private static Connection conn;
    private static UserService us;
    private static GoodsService gs;

    public static UserService getUserService() {
        if (us == null) {
            synchronized (ServiceFactory.class) {
                if (us == null) {
                    us = new UserService(getConnection());
                }
            }
        }
        return us;
    }

    public static GoodsService getGoodsService() {
        if (gs == null) {
            synchronized (ServiceFactory.class) {
                if (gs == null) {
                    gs = new GoodsService(getConnection());
                }
            }
        }
        return gs;
    }

    private static Connection getConnection() {
        if (conn == null) {
            synchronized (ServiceFactory.class) {
                if (conn == null) {
                    try {
                        conn = JDBCUtil.getConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return conn;
    }

    public static void close() {
        JDBCUtil.close(conn);
    }
}
