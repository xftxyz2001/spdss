package com.xftxyz.smms.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.xftxyz.smms.utils.Debug;
import com.xftxyz.smms.utils.JDBCUtil;

public class ServiceFactory {
    // 单例
    private ServiceFactory() {
    }

    private static Connection conn;

    private static UserService userService;
    private static GoodsService goodsService;
    private static SupplierService supplierService;
    private static PurchaseService purchaseService;
    private static SaleService saleService;

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (ServiceFactory.class) {
                if (userService == null) {
                    userService = new UserService(getConnection());
                }
            }
        }
        return userService;
    }

    public static GoodsService getGoodsService() {
        if (goodsService == null) {
            synchronized (ServiceFactory.class) {
                if (goodsService == null) {
                    goodsService = new GoodsService(getConnection());
                }
            }
        }
        return goodsService;
    }

    public static SupplierService getSupplierService() {
        if (supplierService == null) {
            synchronized (ServiceFactory.class) {
                if (supplierService == null) {
                    supplierService = new SupplierService(getConnection());
                }
            }
        }
        return supplierService;
    }

    public static PurchaseService getPurchaseService() {
        if (purchaseService == null) {
            synchronized (ServiceFactory.class) {
                if (purchaseService == null) {
                    purchaseService = new PurchaseService(getConnection());
                }
            }
        }
        return purchaseService;
    }

    public static SaleService getSaleService() {
        if (saleService == null) {
            synchronized (ServiceFactory.class) {
                if (saleService == null) {
                    saleService = new SaleService(getConnection());
                }
            }
        }
        return saleService;
    }

    private static Connection getConnection() {
        if (!checkConnection()) {
            synchronized (ServiceFactory.class) {
                if (!checkConnection()) {
                    try {
                        conn = JDBCUtil.getConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Debug.log("数据库配置文件错误");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        Debug.log("数据库驱动加载失败");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Debug.log("数据库连接失败");
                    }
                }
            }
        }
        return conn;
    }

    public static void close() {
        JDBCUtil.close(conn);
    }

    public static boolean checkConnection() {
        return conn != null;
    }

    
}
