package com.xftxyz.smms.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.PurchaseDao;
import com.xftxyz.smms.entity.Purchase;

public class PurchaseDaoImpl extends BaseDao<Purchase> implements PurchaseDao {

    @Override
    public int addPurchase(Connection conn, Purchase purchase) {
        String sql = "insert into purchases(supplierName, goodsName, price, num, unit, time) values(?, ?, ?, ?, ?, ?)";
        return insert(conn, sql, purchase.getSupplierName(), purchase.getGoodsName(), purchase.getPrice(),
                purchase.getNum(), purchase.getUnit(), purchase.getTime());
    }

    @Override
    public boolean deletePurchase(Connection conn, int id) {
        String sql = "delete from purchases where id = ?";
        return update(conn, sql, id) > 0;
    }

    @Override
    public boolean updatePurchase(Connection conn, Purchase purchase) {
        String sql = "update purchases set supplierName = ?, goodsName = ?, price = ?, num = ?, unit = ?, time = ? where id = ?";
        return update(conn, sql, purchase.getSupplierName(), purchase.getGoodsName(), purchase.getPrice(),
                purchase.getNum(), purchase.getUnit(), purchase.getTime(), purchase.getId()) > 0;
    }

    @Override
    public List<Purchase> getAllPurchases(Connection conn) {
        String sql = "select * from purchases";
        return getBeanList(conn, sql);
    }

}
