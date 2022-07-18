package com.xftxyz.smms.dao;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.entity.Purchase;

public interface PurchaseDao {
    // 新增进货记录
    int addPurchase(Connection conn, Purchase purchase);

    // 根据编号删除进货记录
    boolean deletePurchase(Connection conn, int id);

    // 根据编号修改其他信息
    boolean updatePurchase(Connection conn, Purchase purchase);

    // 查询所有进货记录
    List<Purchase> getAllPurchases(Connection conn);
}
