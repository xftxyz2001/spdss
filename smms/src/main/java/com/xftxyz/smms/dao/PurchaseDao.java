package com.xftxyz.smms.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.xftxyz.smms.entity.Purchase;

public interface PurchaseDao {
    // 新增进货记录
    int addPurchase(Connection conn, Purchase purchase);

    // 根据商品编号查询进货记录
    List<Purchase> getPurchaseByGoodsId(Connection conn, int goodsId);

    // 根据供货商编号查询进货记录
    List<Purchase> getPurchaseBySupplierId(Connection conn, int supplierId);

    // 根据价格范围查询进货记录
    List<Purchase> getPurchaseByPriceRange(Connection conn, BigDecimal minPrice, BigDecimal maxPrice);

    // 根据进货时间范围查询进货记录
    List<Purchase> getPurchaseByTimeRange(Connection conn, Timestamp minTime, Timestamp maxTime);

    // 根据编号修改其他信息
    boolean updatePurchase(Connection conn, Purchase purchase);

    // 根据编号删除进货记录
    boolean deletePurchase(Connection conn, int id);

}
