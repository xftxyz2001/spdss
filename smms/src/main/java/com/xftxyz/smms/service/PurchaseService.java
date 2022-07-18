package com.xftxyz.smms.service;

import java.io.File;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.xftxyz.smms.dao.PurchaseDao;
import com.xftxyz.smms.dao.impl.PurchaseDaoImpl;
import com.xftxyz.smms.entity.Purchase;
import com.xftxyz.smms.utils.FileUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PurchaseService {
    private Connection conn; // 数据库连接对象，通过构造方法初始化
    // DAO对象
    private PurchaseDao dao;
    // 观察列表
    private ObservableList<Purchase> observableList;
    private Purchase old;

    public PurchaseService(Connection conn) {
        this.conn = conn;
        dao = new PurchaseDaoImpl();
        initialize();
    }

    /**
     * 优化方向：保存过滤条件
     */
    private void initialize() {
        if (observableList == null) {
            observableList = FXCollections.observableArrayList();
        } else {
            observableList.clear();
        }
        List<Purchase> purchases = dao.getAllPurchases(conn);
        if (purchases != null) {
            observableList.addAll(purchases);
        }
    }

    public ObservableList<Purchase> getObservableList() {
        return observableList;
    }

    // 添加采购记录
    public boolean addPurchase(Purchase purchase) {
        if (purchase == null) {
            return false;
        }
        if (purchase.getTime() == null) {
            purchase.setTime(Timestamp.valueOf(LocalDateTime.now()));
        }

        int index = dao.addPurchase(conn, purchase);
        if (index < 0) {
            return false;
        }
        purchase.setId(index);

        if (ServiceFactory.getGoodsService().updateBy(purchase)) {

            observableList.add(purchase);
            return true;
        }
        return false;
    }

    // 删除采购记录
    public boolean deletePurchase(Purchase purchase) {
        if (purchase == null) {
            return false;
        }

        boolean isSucc = dao.deletePurchase(conn, purchase.getId());
        if (!isSucc) {
            return false;
        }
        observableList.remove(purchase);
        return true;
    }

    public Purchase getUpdateCopy(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        this.old = purchase;
        return purchase.copy();
    }

    // 更新采购记录
    public boolean updatePurchase(Purchase newPurchase) {
        if (newPurchase == null) {
            return false;
        }

        boolean isSucc = dao.updatePurchase(conn, newPurchase);
        if (!isSucc) {
            return false;
        }
        observableList.set(observableList.indexOf(this.old), newPurchase);
        this.old = null;
        return true;
    }

    // 导出
    public void export(File file) {
        FileUtil.writeExcel(file, Purchase.class, observableList);
    }

}
