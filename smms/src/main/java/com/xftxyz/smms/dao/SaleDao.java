package com.xftxyz.smms.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import com.xftxyz.smms.entity.Sale;

public interface SaleDao {
    // 新增销售记录
    boolean addSale(Connection conn, Sale sale);

    // 根据商品编号查询销售记录
    List<Sale> getSaleByGoodsId(Connection conn, int goodsId);

    // 根据时间范围查询销售记录
    List<Sale> getSaleByTimeRange(Connection conn, Timestamp minTime, Timestamp maxTime);

    // 根据价格范围查询销售记录
    List<Sale> getSaleByPriceRange(Connection conn, BigDecimal minPrice, BigDecimal maxPrice);

    // 根据编号修改其他信息
    boolean updateSale(Connection conn, Sale sale);

    // 根据编号删除销售记录
    boolean deleteSale(Connection conn, int id);
}
