package com.xftxyz.smms.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.xftxyz.smms.dao.BaseDao;
import com.xftxyz.smms.dao.PurchaseDao;
import com.xftxyz.smms.entity.Purchase;

public class PurchaseDaoImpl extends BaseDao<Purchase> implements PurchaseDao {

    @Override
    public int addPurchase(Connection conn, Purchase purchase) {
        /*
         * private int supplierId; // 供应商编号
         * private int goodsId; // 商品编号
         * private BigDecimal price; // 采购价
         * private BigDecimal number; // 采购数量
         * private String unit; // 单位
         * private Timestamp createdAt; // 采购时间
         */
        String sql = "insert into purchase(supplierId, goodsId, price, number, unit, createdAt) values(?, ?, ?, ?, ?, ?)";
        return update(conn, sql, purchase.getSupplierId(), purchase.getGoodsId(), purchase.getPrice(),
                purchase.getNumber(), purchase.getUnit(), purchase.getCreatedAt());
    }

    @Override
    public List<Purchase> getPurchaseByGoodsId(Connection conn, int goodsId) {
        String sql = "select * from purchase where goodsId = ?";
        return getBeanList(conn, sql, goodsId);
    }

    @Override
    public List<Purchase> getPurchaseBySupplierId(Connection conn, int supplierId) {
        String sql = "select * from purchase where supplierId = ?";
        return getBeanList(conn, sql, supplierId);
    }

    @Override
    public List<Purchase> getPurchaseByPriceRange(Connection conn, BigDecimal minPrice, BigDecimal maxPrice) {
        String sql = "select * from purchase where price between ? and ?";
        return getBeanList(conn, sql, minPrice, maxPrice);
    }

    @Override
    public List<Purchase> getPurchaseByTimeRange(Connection conn, Timestamp minTime, Timestamp maxTime) {
        String sql = "select * from purchase where createdAt between ? and ?";
        return getBeanList(conn, sql, minTime, maxTime);
    }

    @Override
    public boolean updatePurchase(Connection conn, Purchase purchase) {
        String sql = "update purchase set supplierId = ?, goodsId = ?, price = ?, number = ?, unit = ?, createdAt = ? where id = ?";
        return update(conn, sql, purchase.getSupplierId(), purchase.getGoodsId(), purchase.getPrice(),
                purchase.getNumber(), purchase.getUnit(), purchase.getCreatedAt(), purchase.getId()) > 0;
    }

    @Override
    public boolean deletePurchase(Connection conn, int id) {
        String sql = "delete from purchase where id = ?";
        return update(conn, sql, id) > 0;
    }

}
